package com.blogspot.nhu313.mastermind.pattern;

import com.blogspot.nhu313.mastermind.ui.ColorCode;

public class Pattern {
	private static final int NOT_FOUND_INDEX = -1;
	private ColorCode[] colorPattern;
	
	public Pattern(ColorCode... code){
		if (code == null || code.length < 1){
			throw new IllegalArgumentException("Number of code should be 1 or greater.");
		}
		colorPattern = code;
	}

	public int size() {
		return colorPattern.length;
	}

	/**
	 * 
	 * @param otherPattern
	 * @return Result code that is sorted with the correct one in the fron
	 */
	public ResultCode[] isSamePattern(Pattern otherPattern) {
		if (otherPattern.size() != this.size()){
			throw new IllegalArgumentException("The compare pattern should be the same size as this pattern.");
		}
		
		final int patternSize = this.size();

		ResultCode[] results = new ResultCode[patternSize];
		ColorCode[] otherPatternValues = otherPattern.colorPattern;
		ColorCode[] originalPatternValues = getOriginalPattern(patternSize);

		int numberOfCorrectResult = removeCorrectResult(otherPatternValues, originalPatternValues);
		populateResult(results, ResultCode.CORRECT, numberOfCorrectResult, 0);

		if (numberOfCorrectResult < patternSize){
			int numberOfCorrectColorResult = removeCorrectColorOnlyResult(otherPatternValues, originalPatternValues);
			populateResult(results, ResultCode.CORRECT_COLOR_ONLY, numberOfCorrectColorResult, numberOfCorrectResult);

			int numberOfCorrect = numberOfCorrectResult + numberOfCorrectColorResult;
			if (numberOfCorrect < patternSize){
				int numberOfIncorrectResult = patternSize - (numberOfCorrect);
				populateResult(results, ResultCode.INCORRECT, numberOfIncorrectResult, numberOfCorrect);
			}
		}
		return results;
	}

	protected int removeCorrectColorOnlyResult(ColorCode[] otherPatternValues, ColorCode[] originalPatternValues) {
		int numberOfCorrectColorResult = 0;
		for (int i = 0; i < originalPatternValues.length; i++){
			ColorCode code = otherPatternValues[i];
				int indexOfCodeInOriginalArray = getIndexOfCode(originalPatternValues, code);
				if (indexOfCodeInOriginalArray != NOT_FOUND_INDEX && originalPatternValues[indexOfCodeInOriginalArray] != null){
					originalPatternValues[indexOfCodeInOriginalArray] = null;
					numberOfCorrectColorResult++;
				}
		}
		return numberOfCorrectColorResult;
	}

	private void populateResult(ResultCode[] results, ResultCode code, int size, int startIndex) {
		for (int i = startIndex; i < size + startIndex; i++){
			results[i] = code;
		}
	}

	private int removeCorrectResult(ColorCode[] originalPatternValues, ColorCode[] otherPatternValues) {
		int numberOfCorrectResult = 0;
		for (int i = 0; i < originalPatternValues.length; i++){
			if (originalPatternValues[i] == otherPatternValues[i]){
				originalPatternValues[i] = null;
				numberOfCorrectResult++;
			}
		}
		return numberOfCorrectResult;
	}

	private int getIndexOfCode(ColorCode[] colors, ColorCode code) {
		for (int i = 0; i < colors.length; i++){
			if (colors[i] == code){
				return i;
			}
		}
		
		return NOT_FOUND_INDEX;
	}

	private ColorCode[] getOriginalPattern(final int patternSize) {
		ColorCode[] patternCopy = new ColorCode[patternSize];
		System.arraycopy(colorPattern, 0, patternCopy, 0, patternSize);
		return patternCopy;
	}
}