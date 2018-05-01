package com.maxsopilkov.redstorm.neuralnetwork.callback;

import com.maxsopilkov.redstorm.neuralnetwork.entity.Error;
import com.maxsopilkov.redstorm.neuralnetwork.entity.Result;

/**
 * Callback for neural network
 * @author jlmd
 */
public interface INeuralNetworkCallback {
    /**
     * This method is called when neural network finish his work and all is good
     * @param result Entity to save obtained values
     */
    void success(Result result);

    /**
     * This method is called when neural network finish his work and something is not good
     * @param error Entity to save obtained error
     */
    void failure(Error error);
}
