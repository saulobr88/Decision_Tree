package example.SimpleTable;

import static feature.P.betweenD;
import static feature.P.lessThanD;
import static feature.P.moreThan;
import static feature.P.moreThanD;
import static feature.P.startsWith;
import static feature.PredicateFeature.newFeature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import decisiontree.DecisionTree;
import data.DataSample;
import data.SimpleDataSample;
import feature.Feature;
import label.BooleanLabel;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import com.google.common.collect.Lists;

public class SimpleTable {

	public static void main(String[] args) throws FileNotFoundException, IOException  {
		List<DataSample> trainingData = readData(true);
		DecisionTree tree = new DecisionTree();
		
		List<Feature> features = getFeatures();
		
		tree.train(trainingData, features);
		
		// print tree after training
        tree.printTree();

	}
	
	private static List<Feature> getFeatures() {
		// Quais são os atributos e seus possíveis valores
		Feature zeroA1 = newFeature("A1", 0);
		Feature oneA1 = newFeature("A1", 1);
		Feature zeroA2 = newFeature("A2", 0);
		Feature oneA2 = newFeature("A2", 1);
		Feature zeroA3 = newFeature("A3", 0);
		Feature oneA3 = newFeature("A3", 1);
				
        return Arrays.asList(zeroA1, oneA1, zeroA2, oneA2, zeroA3, oneA3);
	}
	
	private static List<DataSample> readData(boolean training) throws IOException {
		List<DataSample> data = Lists.newArrayList();
		String filename = training ? "train.csv" : "test.csv";
		InputStreamReader stream = new InputStreamReader(SimpleTable.class.getResourceAsStream(filename));
		try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {
            
            // the header elements are used to map the values to the bean (names must match)
            final String[] header = listReader.getHeader(true);
            
            List<Object> values;
            while ((values = listReader.read(getProcessors(training))) != null) {
            	System.out.println(String.format("lineNo=%s, rowNo=%s, data=%s", listReader.getLineNumber(), listReader.getRowNumber(), values));
                data.add(SimpleDataSample.newSimpleDataSample("Y", header, values.toArray()));
            }
        }
		return data;
	}
	
	private static CellProcessor[] getProcessors(boolean training) {
		// Qual o tipo de dado em cada coluna
        if (training) {
            final CellProcessor[] processors = new CellProcessor[] { 
                    new Optional(new ParseInt()),
                    new Optional(new ParseBooleanLabel()),
                    new Optional(new ParseBooleanLabel()),
                    new Optional(new ParseBooleanLabel()),
                    new Optional(new ParseBooleanLabel())
            };
            return processors;
        } else {
            final CellProcessor[] processors = new CellProcessor[] { 
            		 new Optional(new ParseInt()),
                     new Optional(new ParseInt()),
                     new Optional(new ParseInt()),
                     new Optional(new ParseInt())
            };
            return processors;
        }
	} // Fim do getProcessors
	
	private static class ParseBooleanLabel extends ParseBool {
        public Object execute(final Object value, final CsvContext context) {
            Boolean parsed = (Boolean)super.execute(value, context);
            return parsed ? BooleanLabel.TRUE_LABEL : BooleanLabel.FALSE_LABEL;
        }
    }

}
