package it.paneddo.openaimoderation.utils;

import com.google.common.collect.Maps;
import com.theokanning.openai.moderation.ModerationCategoryScores;

import java.util.Map;

public class ModerationUtils {

    public static Map<String, Double> mapCategoryScores(ModerationCategoryScores categoryScores) {
        Map<String, Double> mappedScores = Maps.newHashMap();

        mappedScores.put("hate", categoryScores.getHate());
        mappedScores.put("hateThreatening", categoryScores.getHateThreatening());
        mappedScores.put("sexual", categoryScores.getSexual());
        mappedScores.put("sexualMinors", categoryScores.getSexualMinors());
        mappedScores.put("selfHarm", categoryScores.getSelfHarm());
        mappedScores.put("violence", categoryScores.getViolence());
        mappedScores.put("violenceGraphic", categoryScores.getViolenceGraphic());

        return mappedScores;
    }

}
