import java.util.Arrays;

public class Layer {
    //general class that handles matrix operation
    int nodes_from;
    int nodes_to;
    double[][] weights;
    double[] biases;

    public Layer(int nodes_from, int nodes_to){
        this.nodes_from = nodes_from;
        this.nodes_to = nodes_to;
        weights = new double[nodes_from][nodes_to];
        biases = new double[nodes_to];
        for (int from = 0; from < nodes_from; from++)
            for (int to = 0; to < nodes_to; to++)
                weights[from][to] = randomize_weight();

        for (int to = 0; to < nodes_to; to++)
            biases[to] = randomize_bias();
    }

    private static double randomize_weight(){
        return Math.random() * 2 - 1;
    }

    private static double randomize_bias(){
        return Math.random() * 2 - 1;
    }

    public static double sigmoid(double x){
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public static double sigmoid_derivative(double sigmoid_value){
        return sigmoid_value * (1 - sigmoid_value);
    }

    public double[] get_outputs(double[] inputs){
        double[] outputs = new double[nodes_to];
        for (int to = 0; to < nodes_to; to++){
            outputs[to] = biases[to];
            for (int from = 0; from < nodes_from; from++)
                outputs[to] += inputs[from] * weights[from][to];
            outputs[to] = sigmoid(outputs[to]);
        }
        return outputs;
    }

    public double[] adjust_constants(double[] layer_derivatives, double[] prev_layer_outputs){
        double[] prev_layer_derivatives = new double[nodes_from];

        for (int to = 0; to < nodes_to; to++) {
            biases[to] -= NeuralNet.LEARNING_RATE * layer_derivatives[to];
            for (int from = 0; from < nodes_from; from++)
                weights[from][to] -= NeuralNet.LEARNING_RATE * layer_derivatives[to] * prev_layer_outputs[from];
        }

        for (int from = 0; from < nodes_from; from++){
            prev_layer_derivatives[from] = 0;
            for (int to = 0; to < nodes_to; to++)
                prev_layer_derivatives[from] += layer_derivatives[to] * weights[from][to];
            prev_layer_derivatives[from] *= sigmoid_derivative(prev_layer_outputs[from]);
        }

        return prev_layer_derivatives;
    }
}
//each node has a bias and a weights coming from each node in the prev layer

//can figure out the partial with respect to any weight.
//how much do we need to change that partial? -1 * learning_rate * partial
//error function is the sum of the squares of all of the errors
//


//back prop algorithm:
//sigmoid function is 1 / (1 + e^-x)

