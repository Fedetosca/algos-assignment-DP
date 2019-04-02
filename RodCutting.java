/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
      // A rod of length <= 0 has a cost of 0
      if (rodLength <= 0)
          return 0;
      // Set the maxPrice for a rod to be very small value. We want to maximize this value
      int priceForRod = Integer.MIN_VALUE;
      for (int i = 0; i < rodLength; i++)
          /* Compare the current max with the best price for a rod which is of length rodLength - i - 1 and replace it
           * with the larger value
           **/
          priceForRod = Math.max(
                  priceForRod,
                  lengthPrices[i] + rodCuttingRecur(rodLength - i - 1, lengthPrices)
          );
      return priceForRod;
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
    return 0;
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
