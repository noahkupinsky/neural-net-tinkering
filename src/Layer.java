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
        return 1;
    }

    private static double randomize_bias(){
        return 1;
    }

    public static double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
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
        }
        return outputs;
    }
}
//each node has a bias and a weights coming from each node in the prev layer

//can figure out the partial with respect to any weight.
//how much do we need to change that partial? -1 * learning_rate * partial
//error function is the sum of the squares of all of the errors
//


//back prop algorithm:
//sigmoid function is 1 / (1 + e^-x)

