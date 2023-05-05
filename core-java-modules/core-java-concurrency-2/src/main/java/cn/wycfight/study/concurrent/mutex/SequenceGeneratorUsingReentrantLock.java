package cn.wycfight.study.concurrent.mutex;

import java.util.concurrent.locks.ReentrantLock;

public class SequenceGeneratorUsingReentrantLock extends SequenceGenerator {

    private ReentrantLock mutex = new ReentrantLock();

    @Override
    public int getNextSequence() {
        mutex.lock();
        try {

            return super.getNextSequence();
        } finally {
            mutex.unlock();
        }
    }

}
