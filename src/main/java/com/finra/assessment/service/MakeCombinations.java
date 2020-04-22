package com.finra.assessment.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class MakeCombinations {

    //method to find all possible combinations of words formed from keypad
    public static void combinations(String input, List<String> combinationsList, List<List<Character>> keypad,
                                    int[] digitsArray, String resultString, int index) {
        if (index == -1) {
            combinationsList.add(resultString);
            return;
        }
        int digit = digitsArray[index];
        int length = keypad.get(digit).size();
        String part = input;
        String x = part.replace((part.substring(part.length() - resultString.length())), resultString);
        combinationsList.add(x);

        // one by one replace the digit with each character in the corresponding list and recur for next digit
        for (int i = 0; i < length; i++) {
            combinations(input, combinationsList, keypad, digitsArray, keypad.get(digit).get(i) + resultString,
                    index - 1);
        }
    }

    //Below the method which will give all the possible combination for the given input string
    public ResponseEntity<Object> generateCombinations(String inputString) throws JSONException {
        List<String> result = new ArrayList<String>();
        List<List<Character>> keypadList = Arrays.asList(
                Arrays.asList('0'),
                Arrays.asList('1'),
                Arrays.asList('A', 'B', 'C'),
                Arrays.asList('D', 'E', 'F'),
                Arrays.asList('G', 'H', 'I'),
                Arrays.asList('J', 'K', 'L'),
                Arrays.asList('M', 'N', 'O'),
                Arrays.asList('P', 'Q', 'R', 'S'),
                Arrays.asList('T', 'U', 'V'),
                Arrays.asList('W', 'X', 'Y', 'Z')
        );

        String[] numbers = inputString.split(""); //created an array holding the digits present in the input string
        int[] numbersArr = new int[numbers.length];
        for (int i = 0; i < numbersArr.length; i++) {
            numbersArr[i] = Integer.parseInt(numbers[i]);
        }

        combinations(inputString, result, keypadList, numbersArr, "", numbersArr.length - 1);
        LinkedHashSet<String> finalResult = new LinkedHashSet<String>(result);
        List list=new ArrayList();
        //combinations are added into a list and from the all possible combination, input string is removed
        for (String x : finalResult)
            list.add(x);
        list.remove(inputString);
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray = new JSONArray(list);
        jsonObject.put("count",list.size());
        jsonObject.put("data",jsonArray);
        System.out.println(jsonObject);
        return ResponseEntity.ok(jsonObject.toString());
    }

}
