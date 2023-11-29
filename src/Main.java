public class Main {
    static final int IO_SIZE = 5;
    public static void main(String[] args) {
        NeuralNet net = new NeuralNet(new int[] {IO_SIZE, 40, IO_SIZE});
        for (int i = 0; i < 1000000; i++){
            double[] input = generate_input();
            double[] expected_output = generate_expected_output(input);
            net.process_input(input, expected_output, (i > 1000000 - 11 || i < 11));
        }
    }

    public static double[] generate_input(){
        double[] input = new double[IO_SIZE];

        for (int i = 0; i < IO_SIZE; i++){
            input[i] = (int) (Math.random() * 6);
        }

        return input;
    }

    public static double[] generate_expected_output(double[] input){
        double[] expected_output = new double[IO_SIZE];
        double sum = 0;
        for (int i = 0; i < IO_SIZE; i++)
            sum += input[i];
        int int_sum = (int) sum;
        for (int i = 0; i < IO_SIZE; i++){
            expected_output[i] = int_sum % 2;
            int_sum -= (int_sum % 2);
            int_sum /= 2;
        }
        return expected_output;
    }
}//(0) 1, 0, 1, 1, 1 (0)
///////0, 0, 1, 0, 1