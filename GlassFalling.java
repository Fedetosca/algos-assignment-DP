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
    public int glassFallingMemoized(int floors, int sheets) {
        int[][] glassFloorTrialMemo = new int[sheets + 1][floors];
        for (int i = 0; i <= sheets; i++) {
            for (int j = 0; j < floors; j++) {
                glassFloorTrialMemo[i][j] = Integer.MAX_VALUE;
            }
        }
        return glassFallingMemoizedHelper(floors, sheets, glassFloorTrialMemo);
    }

    public int glassFallingMemoizedHelper(int floors, int sheets, int floorToSheetTrials[][]) {
        if (sheets == 1 || floors == 0 || floors == 1) {
            return floors;
        }
        int minDrops = floors + 1;
        for (int glassCracked, glassSurvived, x = 1; x < floors; x++) {
            glassCracked = floorToSheetTrials[sheets - 1][x - 1];
            glassSurvived = floorToSheetTrials[sheets][floors - x];
            if (glassCracked == Integer.MAX_VALUE) {
                glassCracked = glassFallingMemoizedHelper(x - 1, sheets - 1, floorToSheetTrials);
            }
            if (glassSurvived == Integer.MAX_VALUE) {
                glassSurvived = glassFallingMemoizedHelper(floors - x, sheets, floorToSheetTrials);
            }
            minDrops = Math.min(minDrops, Math.max(glassCracked, glassSurvived));
        }
        if (floors < floorToSheetTrials[0].length) {
            floorToSheetTrials[sheets][floors] = minDrops + 1;
        }
        return minDrops + 1;
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
        int minTrials2Memo = gf.glassFallingMemoized(100, 3);
        int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
        System.out.println(minTrials1Recur + " " + minTrials1Bottom);
        System.out.println(minTrials2Memo + " " + minTrials2Bottom);
    }
}
