package lotto.model;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;
import lotto.bo.Lotto;
import lotto.bo.WinningNumber;
import lotto.model.Checker.WinningPlace;

public class LottoService {
    static final int LOTTO_PRICE = 1000;
    private List<Lotto> lottos;
    private WinningNumber winningNumber;
    private List<WinningPlace> winningResult;

    public void getLottos(String money) {
        validateMoney(money);
        int numberOfLottos = Integer.parseInt(money) / LOTTO_PRICE;

        System.out.printf("\n%d개를 구매했습니다.\n", numberOfLottos);
        for (int i = 0; i < numberOfLottos; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(Lotto.LOTTO_MIN_NUMBER,
                    Lotto.LOTTO_MAX_NUMBER,
                    Lotto.LOTTO_NUMBER_SIZE);
            Lotto lotto = new Lotto(numbers);
            printLottoNumbers(lotto);
            lottos.add(lotto);
        }
    }

    public void getWinningNumber() {
        String winningNumber;
        String bonusNumber = "1";
        System.out.println("\n당첨 번호를 입력해주세요.");
        winningNumber = Console.readLine();
        new WinningNumber(winningNumber, bonusNumber);
        System.out.println("\n보너스 번호를 입력해주세요.");
        bonusNumber = Console.readLine();
        this.winningNumber = new WinningNumber(winningNumber, bonusNumber);
    }

    private void validateMoney(String money) {
        int tempMoney;

        try {
            tempMoney = Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야합니다.");
        }
        if (tempMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000으로 나누어 떨어져야합니다.");
        }
        if (tempMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 로또 한장의 가격을 넘어야 합니다.");
        }
    }

    private void printLottoNumbers(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();

        Collections.sort(numbers);
        System.out.print("[");
        for (int i = 1; i <= numbers.size(); i++) {
            System.out.print(numbers.get(i));
            if (i != numbers.size()) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
