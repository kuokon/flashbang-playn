//
// Flashbang - a framework for creating PlayN games
// Copyright (C) 2011 Three Rings Design, Inc., All Rights Reserved
// http://github.com/threerings/flashbang-playn

package com.threerings.flashbang.tasks;

import tripleplay.util.Interpolator;

import com.threerings.flashbang.GameObject;

public abstract class InterpolatingTask extends TimedTask
{
    public InterpolatingTask (float time, Interpolator interp)
    {
        super(time);
        _interp = interp;
    }

    @Override
    public boolean update (float dt, GameObject obj)
    {
        _elapsedTime += dt;
        return (_elapsedTime >= _totalTime);
    }

    /**
     * Interpolates between the two given values, using the task's elapsedTime and totalTime values
     */
    protected float interpolate (float from, float to)
    {
        return _interp.apply(from, to - from, Math.min(_elapsedTime, _totalTime), _totalTime);
    }

    protected final Interpolator _interp;
}
