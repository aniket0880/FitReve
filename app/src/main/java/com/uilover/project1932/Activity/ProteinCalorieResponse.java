package com.uilover.project1932.Activity;

import java.util.List;

public class ProteinCalorieResponse {
    private List<Hit> hits;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public static class Hit {
        private Fields fields;

        public Fields getFields() {
            return fields;
        }

        public void setFields(Fields fields) {
            this.fields = fields;
        }
    }

    public static class Fields {
        private String item_name;
        private double nf_protein;
        private double nf_calories;
        private double nf_total_fat;
        private double nf_total_carbohydrate;
        private double nf_sugars;
        private double nf_dietary_fiber;
        private double nf_sodium;

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public double getNf_protein() {
            return nf_protein;
        }

        public void setNf_protein(double nf_protein) {
            this.nf_protein = nf_protein;
        }

        public double getNf_calories() {
            return nf_calories;
        }

        public void setNf_calories(double nf_calories) {
            this.nf_calories = nf_calories;
        }

        public double getNf_total_fat() {
            return nf_total_fat;
        }

        public void setNf_total_fat(double nf_total_fat) {
            this.nf_total_fat = nf_total_fat;
        }

        public double getNf_total_carbohydrate() {
            return nf_total_carbohydrate;
        }

        public void setNf_total_carbohydrate(double nf_total_carbohydrate) {
            this.nf_total_carbohydrate = nf_total_carbohydrate;
        }

        public double getNf_sugars() {
            return nf_sugars;
        }

        public void setNf_sugars(double nf_sugars) {
            this.nf_sugars = nf_sugars;
        }

        public double getNf_dietary_fiber() {
            return nf_dietary_fiber;
        }

        public void setNf_dietary_fiber(double nf_dietary_fiber) {
            this.nf_dietary_fiber = nf_dietary_fiber;
        }

        public double getNf_sodium() {
            return nf_sodium;
        }

        public void setNf_sodium(double nf_sodium) {
            this.nf_sodium = nf_sodium;
        }
    }
}
