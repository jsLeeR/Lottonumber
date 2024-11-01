package SinglePro;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DuplicationNumberUtils {

    // 중복된 로또 번호를 제외하는 메서드
    public static int[] removeDuplicateNumbers(Set<Integer> excludeNumbers, Set<Integer> includeNumbers) {
        Set<Integer> numbers = new HashSet<>(includeNumbers); // Set 사용하여 중복 방지

        // 랜덤 번호를 추가하여 6개로 맞춤
        while (numbers.size() < 6) {
            int randomNum = (int) (Math.random() * 45) + 1;
            if (!excludeNumbers.contains(randomNum) && !numbers.contains(randomNum)) {
                numbers.add(randomNum);
            }
        }

        // Set을 배열로 변환하고 오름차순 정렬
        int[] result = numbers.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(result); // 번호를 오름차순으로 정렬
        return result;
    }
}