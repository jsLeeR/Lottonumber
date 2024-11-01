package SinglePro;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LottoTestEx {
    // 메인 클래스 제외할 번호 포함할 번호 입력 로또번호 출력

    // 접근제한자 추가
    private static Set<Integer> excludeNumbers; // 제외할 번호 집합
    private static Set<Integer> includeNumbers; // 포함할 번호 집합
    private static Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 스캐너
    private static Set<String> uniqueResults = new HashSet<>(); // 중복 결과 방지
    private static int gameCount = 1; // 게임 번호 카운트

    public static void main(String[] args) {
        while (true) {
            System.out.println("제외할 번호를 입력하세요 (쉼표로 구분, 없으면 엔터): ");
            String excludeInput = scanner.nextLine();
            try {
                excludeNumbers = NumberUtils.parseInputToSet(excludeInput);
                if (excludeNumbers.size() > 39) {
                    System.out.println("제외할 번호는 39개 이하로 입력하십시오.");
                    continue;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            while (true) {
                System.out.println("포함할 번호를 입력하세요 (쉼표로 구분, 없으면 엔터): ");
                String includeInput = scanner.nextLine();
                try {
                    includeNumbers = NumberUtils.parseInputToSet(includeInput);
                    if (includeNumbers.size() > 6) {
                        System.out.println("포함할 번호는 6개 이하로 입력하십시오.");
                        continue;
                    }

                    Set<Integer> intersection = new HashSet<>(excludeNumbers);
                    intersection.retainAll(includeNumbers);
                    if (!intersection.isEmpty()) {
                        System.out.println("겹치는 번호: " + intersection);
                        System.out.println("다시 제외할 번호를 입력하십시오.");
                        break;
                    } else {
                        // generateLotto 호출 부분에 예외 처리를 추가합니다.
                        try {
                            generateLotto(includeNumbers, excludeNumbers);
                        } catch (IOException e) {
                            System.out.println("파일 읽기 오류: " + e.getMessage());
                        }
                        return; // 프로그램 종료
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    // 게임 5개 생성 및 출력 메서드
    public static void generateLotto(Set<Integer> includeNumbers, Set<Integer> excludeNumbers) throws IOException {
        System.out.println("추첨 번호:");
        Set<String> uniqueResults = new HashSet<>(); // 중복 결과를 방지하기 위한 Set

        int gameCount = 1; // 게임 번호 카운트

        while (gameCount <= 5) { // 최대 5번 게임 출력
            int[] lottoNumbers = DuplicationNumberUtils.removeDuplicateNumbers(excludeNumbers, includeNumbers);
            String resultString = Arrays.toString(lottoNumbers);

            // 중복 결과가 아니면 출력하고 Set에 추가
            if (!uniqueResults.contains(resultString)) {
                uniqueResults.add(resultString); // 중복 결과를 방지하기 위해 Set에 추가
                System.out.println(gameCount + "번 게임: " + resultString);
                LottoChecker.checkLotto(lottoNumbers); // LottoChecker 사용
                gameCount++; // 새로운 게임 번호 추가
            }
        }
    }
}
