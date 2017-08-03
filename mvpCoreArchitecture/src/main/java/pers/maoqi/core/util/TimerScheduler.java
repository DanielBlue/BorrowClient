package pers.maoqi.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimerScheduler {

    private static TimerScheduler instance;

    private TimerScheduler() {
        startTimer();
    }

    public static TimerScheduler getInstance() {
        if (instance == null) {
            synchronized (TimerScheduler.class) {
                if (instance == null) {
                    instance = new TimerScheduler();
                }
            }
        }
        return instance;
    }

    // 任务调度实现基础类

    private volatile Timer myTimer;

    private final ArrayList<TaskParameter> myTimerTaskPeriods = new ArrayList<TaskParameter>();
    private final HashMap<Runnable, TimerTask> myTimerTasks = new HashMap<Runnable, TimerTask>();

    private class MyTimerTask extends TimerTask {
        private final Runnable myRunnable;

        MyTimerTask(Runnable runnable) {
            myRunnable = runnable;
        }

        @Override
        public void run() {
            myRunnable.run();
        }
    }

    private class TaskParameter {
        public Runnable runnable;
        public long delayMilliseconds;
        public long periodMilliseconds;

        public TaskParameter(Runnable runnable, long delayMilliseconds, long periodMilliseconds) {
            this.runnable = runnable;
            this.delayMilliseconds = delayMilliseconds;
            this.periodMilliseconds = periodMilliseconds;
        }
    }

    private void addTimerTaskInternal(Runnable runnable,
                                      long delayMilliseconds, long periodMilliseconds) {
        final TimerTask task = new MyTimerTask(runnable);
        myTimer.schedule(task, delayMilliseconds, periodMilliseconds);
        myTimerTasks.put(runnable, task);
    }

    private void addTimerTaskInternal(Runnable runnable, long delayMilliseconds) {
        final TimerTask task = new MyTimerTask(runnable);
        myTimer.schedule(task, delayMilliseconds);
        myTimerTasks.put(runnable, task);
    }

    private final Object myTimerLock = new Object();

    public final void startTimer() {
        synchronized (myTimerLock) {
            if (myTimer == null) {
                myTimer = new Timer();
                for (TaskParameter tp : myTimerTaskPeriods) {
                    if (tp.periodMilliseconds < 0) {
                        addTimerTaskInternal(tp.runnable, tp.delayMilliseconds);
                    } else {
                        addTimerTaskInternal(tp.runnable, tp.delayMilliseconds, tp.periodMilliseconds);
                    }
                }
            }
        }
    }

    public final void stopTimer() {
        synchronized (myTimerLock) {
            if (myTimer != null) {
                myTimer.cancel();
                myTimer = null;
                myTimerTasks.clear();
            }
            instance = null;
            System.gc();
        }
    }

    public final void addTimerTask(Runnable runnable, long delayMilliseconds,
                                   long periodMilliseconds) {
        synchronized (myTimerLock) {
            removeTimerTask(runnable);
            myTimerTaskPeriods.add(new TaskParameter(runnable,
                    delayMilliseconds, periodMilliseconds));
            if (myTimer != null) {
                if (periodMilliseconds < 0) {
                    addTimerTaskInternal(runnable, delayMilliseconds);
                } else {
                    addTimerTaskInternal(runnable, delayMilliseconds, periodMilliseconds);
                }
            }
        }
    }

    public final void addTimerTask(Runnable runnable, long delayMilliseconds) {
        synchronized (myTimerLock) {
            removeTimerTask(runnable);
            myTimerTaskPeriods.add(new TaskParameter(runnable,
                    delayMilliseconds, -1));
            if (myTimer != null) {
                addTimerTaskInternal(runnable, delayMilliseconds);
            }
        }
    }

    public final boolean isTimerTaskRunning(Runnable runnable) {
        synchronized (myTimerLock) {
            TimerTask task = myTimerTasks.get(runnable);
            if (task != null) {
                return true;
            }
            return false;
        }
    }

    public final void removeTimerTask(Runnable runnable) {
        synchronized (myTimerLock) {
            TimerTask task = myTimerTasks.get(runnable);
            if (task != null) {
                task.cancel();
                myTimerTasks.remove(runnable);
            }
            for (TaskParameter tp : myTimerTaskPeriods) {
                if (tp.runnable == runnable) {
                    myTimerTaskPeriods.remove(tp);
                    break;
                }
            }
        }
    }

    public final void removeTimerAllTask() {
        synchronized (myTimerLock) {
            for (Map.Entry<Runnable, TimerTask> entry : myTimerTasks.entrySet()) {
                TimerTask task = entry.getValue();
                if (task != null) {
                    task.cancel();
                }
            }
            // 清除所有任务
            myTimerTasks.clear();
            //
            myTimerTaskPeriods.clear();
        }
    }
}
