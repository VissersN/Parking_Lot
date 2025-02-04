import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;


public class ParkingTest extends StageTest<String> {
    List<String> testCaseInputs = List.of(
            "White\nGreen\nYellow",
            "Black\nBlue\nSilver"
    );

    public List<TestCase<String>> generate() {
        return testCaseInputs.stream()
                .map(
                        testCaseInput -> new TestCase<String>()
                                .setInput(testCaseInput)
                                .setAttach(testCaseInput))
                .toList();
    }

    @Override
    public CheckResult check(String reply, String clue) {
        String[] colors = clue.trim().split("\n");

        String[] lines = new String[] {
                colors[0] + " car has parked.",
                colors[1] + " car left the parking lot.",
                colors[2] + " car just parked here."
        };

        String[] outputLines = reply.trim().split("\n");

        if (outputLines.length != 3) {
            return CheckResult.wrong(
                    String.format(
                            "Output contains %s line(s), expected: 3",
                            outputLines.length));
        }

        for (int i = 0; i < 3; i++) {
            if (!outputLines[i].trim().equals(lines[i])) {
                return CheckResult.wrong(
                        String.format(
                                "Line %d in output is wrong. " +
                                        "Make sure you're taking the car's color as input from the console.\n" +
                                        "Please take a look at the example.",
                                i + 1));
            }
        }

        return CheckResult.correct();
    }
}
