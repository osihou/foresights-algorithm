import db.OpinionProcessor;
import db.collectors.SentimentCollector;
import db.SqliteController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ForeSightsAlgorithm {



    public static void main(String[] args) throws Exception {

            OpinionProcessor opinionProcessor = new OpinionProcessor();
            opinionProcessor.processOpinion();

    }
}
