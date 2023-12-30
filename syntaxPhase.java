import java.util.ArrayList;
import java.util.List;

public class syntaxPhase {

    public int getNullableRuleIndex(String[] grammerRule) {
        int ruleIndex = 0;
        for (int i = 0; i < grammerRule.length; i++) {
            if (grammerRule[i].contains("#")) {
                ruleIndex = i;
            }
        }
        return ruleIndex;
    }
// ! *************************************************************************************************

    public Character getNullableNonTerminalSymbol(String str) {
        return str.charAt(0);
    }

    // ! *************************************************************************************************

    public List<String> getBeginDirectlyWith(String[] rule, int nullableRuleTerminalIndex,
            Character nullableNonTerminal) {

        String firstRule;
        String secondRule;
        String normalRule;
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < rule.length; i++) {
            if (i == nullableRuleTerminalIndex) {

            } else if (rule[i].charAt(2) == nullableNonTerminal) {
                firstRule = rule[i].charAt(0) + " BDW " + rule[i].charAt(2);
                stringList.add(firstRule);

                secondRule = rule[i].charAt(0) + " BDW " + rule[i].charAt(3);
                stringList.add(secondRule);

            } else {
                normalRule = rule[i].charAt(0) + " BDW " + rule[i].charAt(2);
                stringList.add(normalRule);
            }
        }

        return stringList;

    }

// ! *************************************************************************************************

    public List<String> getBeginWith(String[] rule, int nullableRuleTerminalIndex, Character nullableNonTerminal) {

        String firstRule = null;
        String secondRule = null;
        String normalRule = null;
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < rule.length; i++) {
            if (i == nullableRuleTerminalIndex) {
            } else if (rule[i].charAt(2) == nullableNonTerminal) {
                firstRule = rule[i].charAt(0) + " BW " + rule[i].charAt(2);
                stringList.add(firstRule);

                secondRule = rule[i].charAt(0) + " BW " + rule[i].charAt(3);
                stringList.add(secondRule);

            } else {
                normalRule = rule[i].charAt(0) + " BW " + rule[i].charAt(2);
                stringList.add(normalRule);
            }
        }

        return stringList;

    }

// ! *************************************************************************************************

    public List<String> getBeginWithReflexive(List<String> list) {
        List<String> BWList = new ArrayList<>();
        List<String> BWList2 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String val = list.get(i).charAt(0) + " BW " + list.get(i).charAt(0);
            BWList.add(val);

            String val2 = list.get(i).charAt(6) + " BW " + list.get(i).charAt(6);
            BWList.add(val2);
        }
        for (int i = 0; i < BWList.size(); i++) {
            if (!BWList2.contains(BWList.get(i))) {
                BWList2.add(BWList.get(i));
            }
        }
        return BWList2;
    }

// ! *************************************************************************************************

    public List<String> getBeginWithTrasitive(List<String> list) {
        List<String> BWList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).charAt(6) == list.get(j).charAt(0)) {
                    String val = list.get(i).charAt(0) + " BW " + list.get(j).charAt(6);
                    BWList.add(val);
                }
            }
        }

        return BWList;

    }

    
// ! *************************************************************************************************
    /**
     * S → ABc
     * A → bA
     * A → є
     * B → c
     * 
     * @param args
     */

    public static void main(String[] args) {
        String rule[] = new String[] { "S→ABc", "A→bA",
                "A→#", "B→c" };
        syntaxPhase algorithm = new syntaxPhase();

        int nullableRuleIndex = algorithm.getNullableRuleIndex(rule);
        System.out.println(" ");
        System.out.println(" --------Step 1--------");
        System.out.println(" ");
        System.out.println("The Nullable Rule is : " + nullableRuleIndex);

        Character nullableNonTerminial = algorithm.getNullableNonTerminalSymbol(rule[nullableRuleIndex]);
        System.out.println("The Nullable Non Terminal is : " + nullableNonTerminial);

        List<String> BDW = algorithm.getBeginDirectlyWith(rule, nullableRuleIndex, nullableNonTerminial);
        System.out.println(" ");
        System.out.println(" --------Step 2--------");
        System.out.println(" ");
        System.out.println(BDW);

        List<String> BW = algorithm.getBeginWith(rule, nullableRuleIndex, nullableNonTerminial);
        System.out.println(BW);
        List<String> BWReflexsive = algorithm.getBeginWithReflexive(BDW);
        System.out.println(BWReflexsive);
        List<String> BWTransitive = algorithm.getBeginWithTrasitive(BDW);
        System.out.println(BWTransitive);
        List<String> BW_All = new ArrayList<>();
        BW_All.addAll(BW);
        BW_All.addAll(BWReflexsive);
        BW_All.addAll(BWTransitive);
    }
}
