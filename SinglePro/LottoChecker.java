package SinglePro;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LottoChecker {
	//data.txt 내용과 대조하여 역대 당첨번호와 일치하는 번호를 비교하고 내용 출력
    // data.txt 파일에서 당첨 번호를 읽어와 로또 번호와 비교하는 메서드
    public static void checkLotto(int[] lottoNumbers) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/SinglePro/data.txt"));
        String line;
        int drawCounter = 1; // 회차를 나타내는 변수
        boolean isWinner = false;

        while ((line = reader.readLine()) != null) {
            int[] winningNumbers = Arrays.stream(line.split("\\s+"))
                                         .mapToInt(Integer::parseInt)
                                         .toArray();
            Arrays.sort(winningNumbers);

            if (Arrays.equals(lottoNumbers, winningNumbers)) {
                System.out.println(drawCounter + "회 당첨번호입니다.");
                isWinner = true;
                break;
            }
            drawCounter++;
        }

        
        reader.close();
    }
}
