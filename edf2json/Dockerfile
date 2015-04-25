FROM dockerfile/java:oracle-java8

EXPOSE 8080

COPY biosignals-edf2json.jar /usr/share/biosignals/edf2json/
CMD java -jar /usr/share/biosignals/edf2json/biosignals-edf2json.jar
