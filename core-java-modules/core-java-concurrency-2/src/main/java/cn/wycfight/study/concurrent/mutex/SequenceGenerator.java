package cn.wycfight.study.concurrent.mutex;

public class SequenceGenerator {
    /**
     * 共享变量
     */
    private int currentValue = 0;

    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }

}