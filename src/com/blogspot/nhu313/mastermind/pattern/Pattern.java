package com.blogspot.nhu313.mastermind.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.blogspot.nhu313.mastermind.ui.ColorCode;

public class Pattern {
	private ColorCode[] secretPattern;
	
	public Pattern(ColorCode... code){
		if (code == null || code.length < 1){
			throw new IllegalArgumentException("Number of code should be 1 or greater.");
		}
		secretPattern = code;
	}

	public int size() {
		return secretPattern.length;
	}

	/**
	 * 
	 * @param otherPattern
	 * @return Result code that is sorted with the correct one in the fron
	 */
	public List<ResultCode> isSamePattern(Pattern otherPattern) {
		if (otherPattern.size() != this.size()){
			throw new IllegalArgumentException("The compare pattern should be the same size as this pattern.");
		}
		
		final int patternSize = this.size();

		List<ResultCode> results = new ArrayList<ResultCode>();
		List<ColorCode> otherPatternValues = getColorList(otherPattern);
		List<ColorCode> originalPatternValues = getColorList(this);

		removeSameItemInSamePosition(originalPatternValues, otherPatternValues);
		int numberOfCorrectResult = patternSize - originalPatternValues.size();
		addResultCode(results, ResultCode.CORRECT, numberOfCorrectResult);

		if (results.size() < patternSize){
			int numberOfCorrectColorResult = countCorrectColorOnlyResult(originalPatternValues, otherPatternValues);
			addResultCode(results, ResultCode.CORRECT_COLOR_ONLY, numberOfCorrectColorResult);

			if (results.size() < patternSize){
				int numberOfIncorrectResult = patternSize - results.size();
				addResultCode(results, ResultCode.INCORRECT, numberOfIncorrectResult);
			}
		}
		return results;
	}

	protected int countCorrectColorOnlyResult(List<ColorCode> originalPatternValues, List<ColorCode> otherPatternValues) {
		int numberOfCorrectColorResult = 0;
		for (int i = 0; i < originalPatternValues.size(); i++){
			ColorCode code = originalPatternValues.get(i);
			if (otherPatternValues.contains(code)){
				numberOfCorrectColorResult++;
			}
		}
		return numberOfCorrectColorResult;
	}

	private void addResultCode(List<ResultCode> results, ResultCode code, int size) {
		for (int i = 0; i < size; i++){
			results.add(code);
		}
	}

	protected void removeSameItemInSamePosition(List<ColorCode> colors1, List<ColorCode> colors2) {
		for (int i = 0; i < colors1.size(); i++){
			if (colors1.get(i) == colors2.get(i)){
				colors1.set(i, null);
				colors2.set(i, null);
			}
		}
		
		colors1.removeAll(Collections.singleton(null));
		colors2.removeAll(Collections.singleton(null));
	}

	private List<ColorCode> getColorList(Pattern pattern) {
		ColorCode[] codes = pattern.secretPattern;
		//Can't use Arrays.asList() because it doesn't support remove operation
		List<ColorCode> list = new ArrayList<ColorCode>();
		for (ColorCode code : codes){
			list.add(code);
		}
		
		return list;
	}

	public ColorCode[] getSecretCode() {
		return secretPattern;
	}
}