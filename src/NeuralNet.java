import java.util.ArrayList;

public class NeuralNet {
    static final double LEARNING_RATE = 0.05;
    Layer[] layers;
    int num_layers;
    int input_size;
    int output_size;
    public NeuralNet(int[] layer_sizes){
        num_layers = layer_sizes.length - 1;
        input_size = layer_sizes[0];
        output_size = layer_sizes[num_layers];
        layers = new Layer[num_layers];
        for (int i = 0; i < num_layers; i++)
            layers[i] = new Layer(layer_sizes[i], layer_sizes[i+1]);
    }

    public void process_input(double[] input, double[] expected_output, boolean print_results){
        ArrayList<double[]> layer_outputs = new ArrayList<>();
        layer_outputs.add(input);
        double[] previous_output = input;
        for (int layer = 0; layer < num_layers; layer++){
            double[] layer_output = layers[layer].get_outputs(previous_output);
            previous_output = layer_output;
            layer_outputs.add(layer_output);
        }
        double[] final_output = previous_output;

        double error = 0;
        for (int i = 0; i < output_size; i++)
            error += Math.pow(expected_output[i] - final_output[i], 2);

        if (print_results)
            System.out.println("Error: " + error);

        //now we need to go back and update weights
        //each entry of layer derivatives is equal to (del E / del node) * node'(input)
        double[] layer_derivatives = new double[output_size];
        for (int i = 0; i < output_size; i++)
            layer_derivatives[i] = (expected_output[i] - final_output[i]) * final_output[i] * (final_output[i] - 1) * 2;
        for (int layer = num_layers - 1; layer >= 0; layer--){
            layer_derivatives = layers[layer].adjust_constants(layer_derivatives, layer_outputs.get(layer));
        }
    }
}