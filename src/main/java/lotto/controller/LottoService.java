package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoComputer;
import lotto.domain.LottoGenerator;
import lotto.domain.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;


public class LottoService {

    private final InputView inputView;
    private final OutputView outputView;
    private LottoGenerator lottoGenerator;
    private LottoComputer lottoComputer;
    private Lottos myLottos;
    private Lotto winnerLotto;

    public LottoService(InputView inputView, OutputView outputView, LottoComputer lottoComputer,
                        LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoComputer = lottoComputer;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        handleUserMoney();

        myLottos = lottoGenerator.generateMyTickets();
        outputView.showMyTickets(myLottos.toDto());

        handleWinnerNumber();
        handleBonusNumber();

        lottoComputer.config(myLottos);
        outputView.showResult(lottoComputer.simulate());
    }

    private void handleWinnerNumber() {
        outputView.askWinnerNumber();
        while (true) {
            try {
                winnerLotto = Lotto.toLotto(inputView.readWinnerNumber());
                lottoComputer.config(winnerLotto);
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private void handleBonusNumber() {
        outputView.askBonusNumber();
        while (true) {
            try {
                lottoComputer.config(inputView.readBonusNumber());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void handleUserMoney() {
        outputView.askMoney();
        while (true) {
            try {
                lottoGenerator.insertMoney(inputView.readMoney());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}