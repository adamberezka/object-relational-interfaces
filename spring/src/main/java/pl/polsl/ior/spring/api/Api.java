package pl.polsl.ior.spring.api;

public abstract class Api {
    public static final String API_BASE = "/api/v1";

    public static class Course {
        public static final String ENDPOINT = API_BASE + "/courses";
    }

    public static class Flight {
        public static final String ENDPOINT = API_BASE + "/flights";
    }

    public static class Student {
        public static final String ENDPOINT = API_BASE + "/students";

    }
}
