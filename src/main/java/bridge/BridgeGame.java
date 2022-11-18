package bridge;

import bridge.utils.Validator;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final BridgeOfComputer computerMap;
    private final BridgeOfUser userMap;
    private int stage;
    private boolean done;

    public BridgeGame(List<String> answerBridgeMap) {
        this.computerMap = new BridgeOfComputer(answerBridgeMap);
        this.userMap = new BridgeOfUser();
        this.stage = 0;
        this.done = false;
    }

    public void move(String moveSide) {
        Validator.checkIsSide(moveSide);
        boolean isRight = computerMap.match(stage, moveSide);
        if (isRight) {
            restoreRightMove(moveSide);
        }
        if (!isRight) {
            restoreWrongMove(moveSide);
        }
    }

    private void restoreRightMove(String moveSide) {
        userMap.restoreByRigth(moveSide);
        stage++;
    }

    private void restoreWrongMove(String moveSide) {
        userMap.restoreByWrong(moveSide);
        done = true;
    }

    public BridgeOfUser nowUserMapState() {
        return userMap;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }


    public boolean isNotDone() {
        return !done;
    }
}
