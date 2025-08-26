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
        
        // Ler os dados de teste
        List<DataSample> testingData = readData(false);
        List<String> predictions = Lists.newArrayList();
        
        // classifica todos os dados
        for (DataSample dataSample : testingData) {
            predictions.add(dataSample.getValue("ID").get() + "," + tree.classify(dataSample).getPrintValue());
        }
        // escreve no arquivo de previões
        FileWriter fileWriter = new FileWriter(new File("predictions_simpleTable.csv"));
        fileWriter.append("ID,Y").append("\n");
        for (String prediction : predictions) {
            fileWriter.append(prediction).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
	}
	
	private static List<Feature> getFeatures() {
		// Quais são os atributos e seus possíveis valores
		Feature isMale = newFeature("A1", "male");
        Feature isFemale = newFeature("A1", "female");
		Feature yesA2 = newFeature("A2", "yes");
		Feature noA2 = newFeature("A2", "no");
		
		return Arrays.asList(isMale, isFemale, yesA2, noA2);
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
                    new Optional(new ParseInt()), // ID
                    new Optional(),
                    new Optional(),
                    new Optional(new ParseBooleanLabel()) // Y
            };
            return processors;
        } else {
            final CellProcessor[] processors = new CellProcessor[] { 
            		new Optional(new ParseInt()), // ID
                    new Optional(),
                    new Optional()
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
