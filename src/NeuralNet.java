import java.util.ArrayList;

public class NeuralNet {
    static final double LEARNING_RATE = 0.01;
    Layer[] layers;
    int num_layers;
    public NeuralNet(int[] layer_sizes){
        num_layers = layer_sizes.length - 1;
        layers = new Layer[num_layers];
        for (int i = 0; i < num_layers; i++)
            layers[i] = new Layer(layer_sizes[i], layer_sizes[i+1]);
    }

    public void process_input(double[] input, double[] expected_output){
        ArrayList<double[]> layer_outputs = new ArrayList<double[]>();
        double[] previous_output = input;
        for (int layer = 0; layer < num_layers; layer++){
            double[] layer_output = layers[layer].get_outputs(previous_output);
            previous_output = layer_output;
            layer_outputs.add(layer_output);
        }

        double error = 0;
        for (int i = 0; i < expected_output.length; i++)
            error += Math.pow(expected_output[i] - previous_output[i], 2);
    }
}
