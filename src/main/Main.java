package main;

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

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<DataSample> trainingData = readData(true);
		DecisionTree tree = new DecisionTree();
		
		List<Feature> features = getFeatures();
		
		tree.train(trainingData, features);
		
		// print tree after training
        tree.printTree();
		
        /*
         * Testes
         */
        /*
		// read test data
        List<DataSample> testingData = readData(false);
        List<String> predictions = Lists.newArrayList();
        
        // classify all test data
        for (DataSample dataSample : testingData) {
            predictions.add(dataSample.getValue("ID").get() + "," + tree.classify(dataSample).getPrintValue());
        }
        // write predictions to file
        FileWriter fileWriter = new FileWriter(new File("predictions_bbc.csv"));
        fileWriter.append("ID,class").append("\n");
        for (String prediction : predictions) {
            fileWriter.append(prediction).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
        */
	}
	
	private static List<Feature> getFeatures() {
		Feature minus = newFeature("-", betweenD(0.000, 0.493), "entre 0.000 e 0.493");
		Feature zero = newFeature("000", betweenD(0.000, 1.081), "entre 0.000 e 1.00");
		Feature doisMileQuatro = newFeature("2004", betweenD(0.000, 1.778), "entre 0.000 e 1.778");
		Feature doisMileCinco = newFeature("2005", betweenD(0.000, 2.059), "entre 0.000 e 2.059");
		Feature analysts = newFeature("analysts", betweenD(0.000, 1.876), "entre 0.000 e 1.876");
		Feature bank = newFeature("bank", betweenD(0.000, 2.006), "entre 0.000 e 2.006");
		Feature budget = newFeature("budget", betweenD(0.000, 2.558), "entre 0.000 e 2.558");
		Feature business = newFeature("business", betweenD(0.000, 1.927), "entre 0.0 e 1.927");
		Feature chief = newFeature("chief", betweenD(0.000, 1.772), "entre 0.000 e 1.772");
		Feature china = newFeature("china", betweenD(0.000, 2.504), "entre 0.000 e 2.504");
		Feature companies = newFeature("companies", betweenD(0.000, 1.574), "entre 0.000 e 1.574");
		Feature company = newFeature("company", betweenD(0.000, 1.371), "entre 0.000 e 1.371");
		Feature country = newFeature("country", betweenD(0.000, 1.616), "entre 0.000 e 1.616");
		Feature deal = newFeature("deal", betweenD(0.000, 2.136), "entre 0.000 e 2.136");
		Feature december = newFeature("december", betweenD(0.000, 2.146), "entre 0.000 e 2.146");
		Feature demand = newFeature("demand", betweenD(0.000, 2.096), "entre 0.000 e 2.096");
		Feature dollar = newFeature("dollar", betweenD(0.000, 2.422), "entre 0.000 e 2.422");
		Feature economic = newFeature("economic", betweenD(0.000, 1.965), "entre 0.000 e 1.965");
		Feature economy = newFeature("economy", betweenD(0.000, 1.883), "entre 0.000 e 1.883");
		Feature europe = newFeature("europe", betweenD(0.000, 2.223), "entre 0.000 e 2.223");
		Feature european = newFeature("european", betweenD(0.000, 1.772), "entre 0.000 e 1.772");
		Feature euros = newFeature("euros", betweenD(0.000, 2.521), "entre 0.000 e 2.521");
		Feature expected = newFeature("expected", betweenD(0.000, 1.919), "entre 0.000 e 1.919");
		Feature financial = newFeature("financial", betweenD(0.000, 1.965), "entre 0.000 e 1.965");
		Feature firm = newFeature("firm", betweenD(0.000, 1.354), "entre 0.000 e 1.354");
		Feature firms = newFeature("firms", betweenD(0.000, 2.006), "entre 0.000 e 2.006");
		Feature foreign = newFeature("foreign", betweenD(0.000, 2.377), "entre 0.000 e 2.377");
		Feature government = newFeature("government", betweenD(0.000, 1.131), "entre 0.000 e 1.131");
		Feature group = newFeature("group", betweenD(0.000, 1.927), "entre 0.000 e 1.927");
		Feature growth = newFeature("growth", betweenD(0.000, 1.748), "entre 0.000 e 1.748");
		Feature interest = newFeature("interest", betweenD(0.000, 2.126), "entre 0.000 e 2.126");
		Feature investment = newFeature("investment", betweenD(0.000, 2.050), "entre 0.000 e 2.050");
		Feature jobs = newFeature("jobs", betweenD(0.000, 2.363), "entre 0.000 e 2.363");
		Feature market = newFeature("market", betweenD(0.000, 1.335), "entre 0.000 e 1.335");
		Feature months = newFeature("months", betweenD(0.000, 1.934), "entre 0.000 e 1.934");
		Feature oil = newFeature("oil", betweenD(0.000, 2.106), "entre 0.000 e 2.106");
		Feature people = newFeature("people", betweenD(0.000, 0.777), "entre 0.000 e 0.777");
		Feature president = newFeature("president", betweenD(0.000, 2.246), "entre 0.000 e 2.246");
		Feature prices = newFeature("prices", betweenD(0.000, 2.068), "entre 0.000 e 2.068");
		Feature profits = newFeature("profits", betweenD(0.000, 2.246), "entre 0.000 e 2.246");
		Feature quarter = newFeature("quarter", betweenD(0.000, 2.200), "entre 0.000 e 2.200");
		Feature rate = newFeature("rate", betweenD(0.000, 2.200), "entre 0.000 e 2.200");
		Feature rates = newFeature("rates", betweenD(0.000, 2.309), "entre 0.000 e 2.309");
		Feature rise = newFeature("rise", betweenD(0.000, 1.965), "entre 0.000 e 1.965");
		Feature sales = newFeature("sales", betweenD(0.000, 2.106), "entre 0.000 e 2.106");
		Feature shares = newFeature("shares", betweenD(0.000, 1.927), "entre 0.000 e 1.927");
		Feature spending = newFeature("spending", betweenD(0.000, 2.422), "entre 0.000 e 2.422");
		Feature stock = newFeature("stock", betweenD(0.000, 2.271), "entre 0.000 e 2.271");
		Feature tax = newFeature("tax", betweenD(0.000, 2.596), "entre 0.000 e 2.596");
		Feature three = newFeature("three", betweenD(0.000, 1.214), "entre 0.000 e 1.214");
		Feature time = newFeature("time", betweenD(0.000, 0.861), "entre 0.000 e 0.861");
		Feature told = newFeature("told", betweenD(0.000, 1.067), "entre 0.000 e 1.067");
		Feature uk = newFeature("uk", betweenD(0.000, 1.029), "entre 0.000 e 1.029");
		Feature will = newFeature("will", betweenD(0.000, 0.287), "entre 0.000 e 0.287");
		Feature year = newFeature("year", betweenD(0.000, 0.569), "entre 0.000 e 0.569");
		Feature years = newFeature("years", betweenD(0.000, 0.979), "entre 0.000 e 0.979");
		Feature yukos = newFeature("yukos", betweenD(0.000, 2.984), "entre 0.000 e 2.984");
		Feature actor = newFeature("actor", betweenD(0.000, 2.189), "entre 0.000 e 2.189");
		Feature album = newFeature("album", betweenD(0.000, 2.407), "entre 0.000 e 2.407");
		Feature award = newFeature("award", betweenD(0.000, 2.157), "entre 0.000 e 2.157");
		Feature awards = newFeature("awards", betweenD(0.000, 2.126), "entre 0.000 e 2.126");
		Feature band = newFeature("band", betweenD(0.000, 2.309), "entre 0.000 e 2.309");
		Feature bbc = newFeature("bbc", betweenD(0.000, 1.242), "entre 0.000 e 1.242");
		Feature best = newFeature("best", betweenD(0.000, 1.547), "entre 0.000 e 1.547");
		Feature britsh = newFeature("britsh", betweenD(0.000, 1.725), "entre 0.000 e 1.725");
		Feature film = newFeature("film", betweenD(0.000, 1.869), "entre 0.000 e 1.869");
		Feature including = newFeature("including", betweenD(0.000, 2.006), "entre 0.000 e 2.006");
		Feature music = newFeature("music", betweenD(0.000, 1.661), "entre 0.000 e 1.661");
		Feature number = newFeature("number", betweenD(0.000, 1.556), "entre 0.000 e 1.556");
		Feature song = newFeature("song", betweenD(0.000, 2.504), "entre 0.000 e 2.504");
		Feature star = newFeature("star", betweenD(0.000, 2.087), "entre 0.000 e 2.087");
		Feature top = newFeature("top", betweenD(0.000, 2.136), "entre 0.000 e 2.136");
		Feature tv = newFeature("tv", betweenD(0.000, 1.778), "entre 0.000 e 1.778");
		Feature won = newFeature("won", betweenD(0.000, 1.465), "entre 0.000 e 1.465");
		Feature bill = newFeature("bill", betweenD(0.000, 2.521), "entre 0.000 e 2.521");
		Feature blair = newFeature("blair", betweenD(0.000, 1.855), "entre 0.000 e 1.855");
		Feature blunkett = newFeature("blunkett", betweenD(0.000, 2.887), "entre 0.000 e 2.887");
		Feature britain = newFeature("britain", betweenD(0.000, 1.973), "entre 0.000 e 1.973");
		Feature brown = newFeature("brown", betweenD(0.000, 2.454), "entre 0.000 e 2.454");
		Feature chancellor = newFeature("chancellor", betweenD(0.000, 2.296), "entre 0.000 e 2.296");
		Feature election = newFeature("election", betweenD(0.000, 1.927), "entre 0.000 e 1.927");
		Feature general = newFeature("general", betweenD(0.000, 2.050), "entre 0.000 e 2.050");
		Feature howard = newFeature("howard", betweenD(0.000, 2.246), "entre 0.000 e 2.246");
		Feature labour = newFeature("labour", betweenD(0.000, 1.760), "entre 0.000 e 1.760");
		Feature law = newFeature("law", betweenD(0.000, 2.271), "entre 0.000 e 2.271");
		Feature leader = newFeature("leader", betweenD(0.000, 1.957), "entre 0.000 e 1.957");
		Feature lord = newFeature("lord", betweenD(0.000, 2.577), "entre 0.000 e 2.577");
		Feature minister = newFeature("minister", betweenD(0.000, 1.635), "entre 0.000 e 1.635");
		Feature ministers = newFeature("ministers", betweenD(0.000, 2.136), "entre 0.000 e 2.136");
		Feature mps = newFeature("mps", betweenD(0.000, 2.189), "entre 0.000 e 2.189");
		Feature party = newFeature("party", betweenD(0.000, 1.890), "entre 0.000 e 1.890");
		Feature plans = newFeature("plans", betweenD(0.000, 1.934), "entre 0.000 e 1.934");
		Feature police = newFeature("police", betweenD(0.000, 2.335), "entre 0.000 e 2.335");
		Feature prime = newFeature("prime", betweenD(0.000, 1.998), "entre 0.000 e 1.998");
		Feature publicc = newFeature("public", betweenD(0.000, 1.848), "entre 0.000 e 1.848");
		Feature report = newFeature("report", betweenD(0.000, 2.322), "entre 0.000 e 2.322");
		Feature secretary = newFeature("secretary", betweenD(0.000, 1.784), "entre 0.000 e 1.784");
		Feature spokesman = newFeature("spokesman", betweenD(0.000, 1.981), "entre 0.000 e 1.981");
		Feature tony = newFeature("tony", betweenD(0.000, 1.897), "entre 0.000 e 1.897");
		Feature tories = newFeature("tories", betweenD(0.000, 2.223), "entre 0.000 e 2.223");
		Feature tory = newFeature("tory", betweenD(0.000, 2.087), "entre 0.000 e 2.087");
		Feature work = newFeature("work", betweenD(0.000, 1.606), "entre 0.000 e 1.606");
		Feature arsenal = newFeature("arsenal", betweenD(0.000, 2.296), "entre 0.000 e 2.296");
		Feature boss = newFeature("boss", betweenD(0.000, 2.006), "entre 0.000 e 2.006");
		Feature chance = newFeature("chance", betweenD(0.000, 2.068), "entre 0.000 e 2.068");
		Feature chelsea = newFeature("chelsea", betweenD(0.000, 2.167), "entre 0.000 e 2.167");
		Feature club = newFeature("club", betweenD(0.000, 1.927), "entre 0.000 e 1.927");
		Feature coach = newFeature("coach", betweenD(0.000, 1.919), "entre 0.000 e 1.919");
		Feature cup = newFeature("cup", betweenD(0.000, 1.965), "entre 0.000 e 1.965");
		Feature don = newFeature("don", betweenD(0.000, 2.087), "entre 0.000 e 2.087");
		Feature england = newFeature("england", betweenD(0.000, 2.032), "entre 0.000 e 2.032");
		Feature finall = newFeature("final", betweenD(0.000, 2.116), "entre 0.000 e 2.116");
		Feature football = newFeature("football", betweenD(0.000, 2.087), "entre 0.000 e 2.087");
		Feature france = newFeature("france", betweenD(0.000, 2.271), "entre 0.000 e 2.271");
		Feature game = newFeature("game", betweenD(0.000, 1.402), "entre 0.000 e 1.402");
		Feature games = newFeature("games", betweenD(0.000, 1.611), "entre 0.000 e 1.611");
		Feature goal = newFeature("goal", betweenD(0.000, 2.246), "entre 0.000 e 2.246");
		Feature going = newFeature("going", betweenD(0.000, 1.919), "entre 0.000 e 1.919");
		Feature good = newFeature("good", betweenD(0.000, 1.815), "entre 0.000 e 1.815");
		Feature great = newFeature("great", betweenD(0.000, 1.957), "entre 0.000 e 1.957");
		Feature injury = newFeature("injury", betweenD(0.000, 2.167), "entre 0.000 e 2.167");
		Feature ireland = newFeature("ireland", betweenD(0.000, 2.335), "entre 0.000 e 2.335");
		Feature league = newFeature("league", betweenD(0.000, 2.068), "entre 0.000 e 2.068");
		Feature liverpool = newFeature("liverpool", betweenD(0.000, 2.558), "entre 0.000 e 2.558");
		Feature manager = newFeature("manager", betweenD(0.000, 2.032), "entre 0.000 e 2.032");
		Feature match = newFeature("match", betweenD(0.000, 2.116), "entre 0.000 e 2.116");
		Feature minutes = newFeature("minutes", betweenD(0.000, 2.296), "entre 0.000 e 2.296");
		Feature olympic = newFeature("olympic", betweenD(0.000, 2.211), "entre 0.000 e 2.211");
		Feature play = newFeature("play", betweenD(0.000, 1.912), "entre 0.000 e 1.912");
		Feature players = newFeature("players", betweenD(0.000, 1.578), "entre 0.000 e 1.578");
		Feature race = newFeature("race", betweenD(0.000, 2.349), "entre 0.000 e 2.349");
		Feature season = newFeature("season", betweenD(0.000, 1.876), "entre 0.000 e 1.876");
		Feature second = newFeature("second", betweenD(0.000, 2.032), "entre 0.000 e 2.032");
		Feature side = newFeature("side", betweenD(0.000, 1.809), "entre 0.000 e 1.809");
		Feature sport = newFeature("sport", betweenD(0.000, 2.041), "entre 0.000 e 2.041");
		Feature start = newFeature("start", betweenD(0.000, 2.116), "entre 0.000 e 2.116");
		Feature team = newFeature("team", betweenD(0.000, 1.766), "entre 0.000 e 1.766");
		Feature united = newFeature("united", betweenD(0.000, 2.258), "entre 0.000 e 2.258");
		Feature ve = newFeature("ve", betweenD(0.000, 2.178), "entre 0.000 e 2.178");
		Feature wales = newFeature("wales", betweenD(0.000, 2.487), "entre 0.000 e 2.487");
		Feature well = newFeature("well", betweenD(0.000, 1.497), "entre 0.000 e 1.497");
		Feature win = newFeature("win", betweenD(0.000, 1.602), "entre 0.000 e 1.602");
		Feature two = newFeature("2", betweenD(0.000, 2.296), "entre 0.000 e 2.296");
		Feature access = newFeature("access", betweenD(0.000, 2.258), "entre 0.000 e 2.258");
		Feature broadband = newFeature("broadband", betweenD(0.000, 2.504), "entre 0.000 e 2.504");
		Feature computer = newFeature("computer", betweenD(0.000, 1.998), "entre 0.000 e 1.998");
		Feature content = newFeature("content", betweenD(0.000, 2.363), "entre 0.000 e 2.363");
		Feature data = newFeature("data", betweenD(0.000, 2.223), "entre 0.000 e 2.223");
		Feature digital = newFeature("digital", betweenD(0.000, 1.998), "entre 0.000 e 1.998");
		Feature help = newFeature("help", betweenD(0.000, 2.116), "entre 0.000 e 2.116");
		Feature industry = newFeature("industry", betweenD(0.000, 2.309), "entre 0.000 e 2.309");
		Feature internet = newFeature("internet", betweenD(0.000, 2.006), "entre 0.000 e 2.006");
		Feature media = newFeature("media", betweenD(0.000, 2.296), "entre 0.000 e 2.296");
		Feature microsoft = newFeature("microsoft", betweenD(0.000, 2.363), "entre 0.000 e 2.363");
		Feature mobile = newFeature("mobile", betweenD(0.000, 2.309), "entre 0.000 e 2.309");
		Feature net = newFeature("net", betweenD(0.000, 2.015), "entre 0.000 e 2.015");
		Feature news = newFeature("news", betweenD(0.000, 1.981), "entre 0.000 e 1.981");
		Feature online = newFeature("online", betweenD(0.000, 1.890), "entre 0.000 e 1.890");
		Feature phone = newFeature("phone", betweenD(0.000, 2.335), "entre 0.000 e 2.335");
		Feature phones = newFeature("phones", betweenD(0.000, 2.521), "entre 0.000 e 2.521");
		Feature search = newFeature("search", betweenD(0.000, 2.802), "entre 0.000 e 2.802");
		Feature security = newFeature("security", betweenD(0.000, 2.521), "entre 0.000 e 2.521");
		Feature service = newFeature("service", betweenD(0.000, 2.032), "entre 0.000 e 2.032");
		Feature services = newFeature("services", betweenD(0.000, 2.223), "entre 0.000 e 2.223");
		Feature site = newFeature("site", betweenD(0.000, 2.407), "entre 0.000 e 2.407");
		Feature sites = newFeature("sites", betweenD(0.000, 2.539), "entre 0.000 e 2.539");
		Feature software = newFeature("software", betweenD(0.000, 2.041), "entre 0.000 e 2.041");
		Feature system = newFeature("system", betweenD(0.000, 2.246), "entre 0.000 e 2.246");
		Feature technology = newFeature("technology", betweenD(0.000, 1.766), "entre 0.000 e 1.766");
		Feature users = newFeature("users", betweenD(0.000, 1.790), "entre 0.000 e 1.790");
		Feature video = newFeature("video", betweenD(0.000, 2.126), "entre 0.000 e 2.126");
		Feature web = newFeature("web", betweenD(0.000, 2.167), "entre 0.000 e 2.167");
		Feature website = newFeature("website", betweenD(0.000, 2.041), "entre 0.000 e 2.041");
		
        return Arrays.asList(minus, zero, doisMileQuatro, doisMileCinco, analysts, bank, budget, 
        		business, chief, china, companies, company, country, deal, december, demand, dollar, 
        		economic, economy, europe, european, euros, expected, financial, firm, firms, foreign, 
        		government, group, growth, interest, investment, jobs, market, months, oil, people, 
        		president, prices, profits, quarter, rate, rates, rise, sales, shares, spending, 
        		stock, tax, three, time, told, uk, will, year, years, yukos, actor, album, award, 
        		awards, band, bbc, best, britsh, film, including, music, number, song, star, top, 
        		tv, won, bill, blair, blunkett, britain, brown, chancellor, election, general, howard, 
        		labour, law, leader, lord, minister, ministers, mps, party, plans, police, prime, 
        		publicc, report, secretary, spokesman, tony, tories, tory, work, arsenal, boss, 
        		chance, chelsea, club, coach, cup, don, england, finall, football, france, game, 
        		games, goal, going, good, great, injury, ireland, league, liverpool, manager, match, 
        		minutes, olympic, play, players, race, season, second, side, sport, start, team, 
        		united, ve, wales, well, win, two, access, broadband, computer, content, data, digital, 
        		help, industry, internet, media, microsoft, mobile, net, news, online, phone, phones, 
        		search, security, service, services, site, sites, software, system, technology, users, 
        		video, web, website);
		
	}
	
	private static List<DataSample> readData(boolean training) throws IOException {
		List<DataSample> data = Lists.newArrayList();
		String filename = training ? "bbc_train_ID.csv" : "test.csv";
		InputStreamReader stream = new InputStreamReader(Main.class.getResourceAsStream(filename));
		try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {
            
            // the header elements are used to map the values to the bean (names must match)
            final String[] header = listReader.getHeader(true);
            
            List<Object> values;
            while ((values = listReader.read(getProcessors(training))) != null) {
//             System.out.println(String.format("lineNo=%s, rowNo=%s, data=%s", listReader.getLineNumber(), listReader.getRowNumber(), values));
                data.add(SimpleDataSample.newSimpleDataSample("class", header, values.toArray()));
            }
        }
		
		return data;
	}
	
	private static CellProcessor[] getProcessors(boolean training) {

		// Os tipos de dados de cada coluna, são 175 colunas para treino e 174 para teste 
        if (training) {
            final CellProcessor[] processors = new CellProcessor[] { 
                    new Optional(new ParseInt()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional()
            };
            return processors;
        } else {
            final CellProcessor[] processors = new CellProcessor[] {
            		new Optional(new ParseInt()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble()),
                    new Optional(new ParseDouble())
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
