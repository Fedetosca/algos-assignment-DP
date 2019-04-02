/**
 * Glass Falling
 */
public class GlassFalling {

    // Do not change the parameters!
    public int glassFallingRecur(int floors, int sheets) {
        if (floors == 0 || floors == 1)
            return floors;

        if (sheets == 1) {
            return floors;
        }


        int minDrops = Integer.MAX_VALUE;
        int tempResult;
        for (int i = 1; i <= floors; i++) {
            tempResult = Math.max(
                    glassFallingRecur(i - 1, sheets - 1),
                    glassFallingRecur(floors - i, sheets));
            minDrops = Math.min(tempResult, minDrops);
        }
        return minDrops + 1;
    }

    // Optional:
    // Pick whatever parameters you want to, just make sure to return an int.
    public int glassFallingMemoized() {
        // Fill in here and change the return
        return 0;
    }

    // Do not change the parameters!
    public int glassFallingBottomUp(int floors, int sheets) {
        int[][] glassToFloorTrials = new int[sheets + 1][floors + 1];
        int minDrops;

        for (int i = 1; i <= sheets; ++i) {
            glassToFloorTrials[i][1] = 1;
            glassToFloorTrials[i][0] = 0;
        }

        for (int j = 1; j <= floors; ++j) {
            glassToFloorTrials[1][j] = j;
        }

        for (int i = 2; i <= sheets; ++i) {
            for (int j = 2; j <= floors; ++j) {
                glassToFloorTrials[i][j] = Integer.MAX_VALUE;
                for (int z = 1; z <= j; ++z) {
                    minDrops = 1 + Math.max(glassToFloorTrials[i - 1][z - 1], glassToFloorTrials[i][j - z]);
                    glassToFloorTrials[i][j] = Math.min(glassToFloorTrials[i][j], minDrops);
                }
            }
        }
        return glassToFloorTrials[sheets][floors];
    }


    public static void main(String args[]) {
        GlassFalling gf = new GlassFalling();

        // Do not touch the below lines of code, and make sure
        // in your final turned-in copy, these are the only things printed
        int minTrials1Recur = gf.glassFallingRecur(27, 2);
        int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
        int minTrials2Recur = gf.glassFallingRecur(100, 3);
        int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
        System.out.println(minTrials1Recur + " " + minTrials1Bottom);
        System.out.println(minTrials2Recur + " " + minTrials2Bottom);
    }
}
