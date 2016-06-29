package com.intentpumin.lsy.intentpumin.network;

import java.util.List;

/**
 * Created by wei on 2016/5/20.
 */
public class Bean {


    /**
     * error : 0
     * status : success
     * date : 2014-05-10
     * results : [{"currentCity":"南京","weather_data":[{"date":"周六(今天, 实时：19℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/dayu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/dayu.png","weather":"大雨","wind":"东南风5-6级","temperature":"18℃"},{"date":"周日","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"阵雨转多云","wind":"西北风4-5级","temperature":"21 ~ 14℃"}]}]
     */

    private int error;
    private String status;
    private String date;
    /**
     * currentCity : 南京
     * weather_data : [{"date":"周六(今天, 实时：19℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/dayu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/dayu.png","weather":"大雨","wind":"东南风5-6级","temperature":"18℃"},{"date":"周日","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"阵雨转多云","wind":"西北风4-5级","temperature":"21 ~ 14℃"}]
     */

    private List<ResultsBean> results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String currentCity;
        /**
         * date : 周六(今天, 实时：19℃)
         * dayPictureUrl : http://api.map.baidu.com/images/weather/day/dayu.png
         * nightPictureUrl : http://api.map.baidu.com/images/weather/night/dayu.png
         * weather : 大雨
         * wind : 东南风5-6级
         * temperature : 18℃
         */

        private List<WeatherDataBean> weather_data;

        public String getCurrentCity() {
            return currentCity;
        }

        public void setCurrentCity(String currentCity) {
            this.currentCity = currentCity;
        }

        public List<WeatherDataBean> getWeather_data() {
            return weather_data;
        }

        public void setWeather_data(List<WeatherDataBean> weather_data) {
            this.weather_data = weather_data;
        }

        public static class WeatherDataBean {
            private String date;
            private String dayPictureUrl;
            private String nightPictureUrl;
            private String weather;
            private String wind;
            private String temperature;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayPictureUrl() {
                return dayPictureUrl;
            }

            public void setDayPictureUrl(String dayPictureUrl) {
                this.dayPictureUrl = dayPictureUrl;
            }

            public String getNightPictureUrl() {
                return nightPictureUrl;
            }

            public void setNightPictureUrl(String nightPictureUrl) {
                this.nightPictureUrl = nightPictureUrl;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
        }
    }
}
