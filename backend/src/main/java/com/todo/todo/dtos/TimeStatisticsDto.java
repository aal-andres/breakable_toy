package com.todo.todo.dtos;

public class TimeStatisticsDto {

    public TimeStatisticsDto(String avg,String low, String med, String high ){
        total_avg = avg;
        low_avg = low;
        medium_avg = med;
        high_avg = high;
    }

    public String total_avg;
    public String low_avg;
    public String medium_avg;
    public String high_avg;
}
