public class Main {
    static final int IO_SIZE = 5;
    public static void main(String[] args) {
        NeuralNet net = new NeuralNet(new int[] {IO_SIZE, 10, IO_SIZE});
        for (int i = 0; i < 1000000; i++){
            double[] input = generate_input();
            double[] expected_output = generate_expected_output(input);
            net.process_input(input, expected_output, (i > 1000000 - 10 || i < 11));
        }
    }

    public static double[] generate_input(){
        double[] input = new double[IO_SIZE];

        for (int i = 0; i < IO_SIZE; i++){
            input[i] = (Math.random() > 0.5 ? 1 : 0);
        }

        return input;
    }

    public static double[] generate_expected_output(double[] input){
        double[] expected_output = new double[IO_SIZE];
        for (int i = 0; i < IO_SIZE; i++){

            int prev = 0;
            int next = 0;
            if (i > 0)
                prev = (int) input[i - 1];

            if (i < IO_SIZE - 1)
                next = (int) input[i + 1];

            expected_output[i] = (prev == next ? 0 : 1);
        }
        return expected_output;
    }
}