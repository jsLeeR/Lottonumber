package SinglePro;



import java.util.HashSet;
import java.util.Set;

public class NumberUtils {
	//잘못된 번호,문자 입력시 처음으로 되돌림 
    // 입력 문자열을 Set<Integer>로 변환하는 메서드
    public static Set<Integer> parseInputToSet(String input) {
        Set<Integer> numberSet = new HashSet<>();
        if (!input.trim().isEmpty()) {
            String[] tokens = input.split(",");
            for (String token : tokens) {
                try {
                    int number = Integer.parseInt(token.trim());
                    if (number < 1 || number > 45) {
                        throw new IllegalArgumentException("잘못된 번호: " + number + ". 1과 45 사이의 숫자를 입력하십시오.");
                    }
                    numberSet.add(number);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("숫자가 아닌 값: " + token + ". 올바른 숫자를 입력하십시오.");
                }
            }
        }
        return numberSet;
    }
}
