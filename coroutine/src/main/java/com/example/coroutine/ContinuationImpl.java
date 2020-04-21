package com.example.coroutine;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.DelayKt;

public class ContinuationImpl implements Continuation<Object> {

    private int label = 0;
    private final Continuation<Unit> completion;

    public ContinuationImpl(Continuation<Unit> completion) {
        this.completion = completion;
    }

    @Override
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override
    public void resumeWith(@NotNull Object o) {
        try {
            Object result = o;
            switch (label) {
                case 0: {
                    log(1);
                    result = new SuspendFunctions().returnSuspended( this);
                    label++;
                    if (isSuspended(result)) return;
                }
                case 1: {
                    log(result);
                    log(2);
                    result = DelayKt.delay(1000, this);
                    label++;
                    if (isSuspended(result)) return;
                }
                case 2: {
                    log(3);
                    result = new SuspendFunctions().returnImmediately( this);
                    label++;
                    if (isSuspended(result)) return;
                }
                case 3:{
                    log(result);
                    log(4);
                }
            }
            completion.resumeWith(Unit.INSTANCE);
        } catch (Exception e) {
            completion.resumeWith(e);
        }
    }

    private boolean isSuspended(Object result) {
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    private void log(Object msg) {
        if (msg == null) {
            msg = "null";
        }
        Log.d("lwl", msg.toString());
    }
}