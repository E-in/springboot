package com.example.entity;

public class Params {
    private String user_id;
    private String name;
    private Integer pageSize;
    private Integer pageNum;

    private String searchTri;

    private String searchPat;

    private String begin_date;

    private String end_date;

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSearchTri() {
        return searchTri;
    }

    public void setSearchTri(String searchTri) {
        this.searchTri = searchTri;
    }

    public String getSearchPat() {
        return searchPat;
    }

    public void setSearchPat(String searchPat) {
        this.searchPat = searchPat;
    }
}
