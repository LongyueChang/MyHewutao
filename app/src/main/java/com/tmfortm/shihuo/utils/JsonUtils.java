package com.tmfortm.shihuo.utils;


import com.alibaba.fastjson.JSON;
import com.tmfortm.shihuo.beans.Banner;
import com.tmfortm.shihuo.beans.Category;
import com.tmfortm.shihuo.beans.Channel;
import com.tmfortm.shihuo.beans.Find_Tuijian;
import com.tmfortm.shihuo.beans.Hot_listing;
import com.tmfortm.shihuo.beans.SurgingModelGoods;
import com.tmfortm.shihuo.beans.Surging_Banner;
import com.tmfortm.shihuo.beans.Surging_Children_category;
import com.tmfortm.shihuo.beans.Surging_Context;
import com.tmfortm.shihuo.beans.Surging_Hottest;
import com.tmfortm.shihuo.beans.Surging_Special_column;
import com.tmfortm.shihuo.beans.Tag_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/12/12.
 */
public class JsonUtils {

    public static JSONObject parse(String data) {
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(data);
            jsonObject=jsonObject.optJSONObject("data");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public static List<Banner>  parseBanner(JSONObject bannerObj) {

        JSONArray jsonArray = bannerObj.optJSONArray("banner");

        List<Banner> banners= JSON.parseArray(jsonArray.toString(),Banner.class);
        List<String> urls=new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {

            urls.add(banners.get(i).getImg_url());

        }


        return banners;
    }

    public static List<Channel> parseChannel(JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.optJSONArray("channel");
        List<Channel> channels = JSON.parseArray(jsonArray.toString(), Channel.class);
        return channels;
    }


    public static List<Tag_info> parseTag_info(JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.optJSONArray("tag_info");

        List<Tag_info> tag_infos = JSON.parseArray(jsonArray.toString(), Tag_info.class);

        return tag_infos;
    }


    public static List<Hot_listing> parseHot_listing(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.optJSONArray("hot_listing");
        List<Hot_listing> hot_listings = JSON.parseArray(jsonArray.toString(), Hot_listing.class);


        return hot_listings;
    }

    public static List<Find_Tuijian> parseFind_Tuijian(String recyclerData) {

        try {
            JSONObject jsonObject=new JSONObject(recyclerData);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            List<Find_Tuijian> find_tuijien = JSON.parseArray(jsonArray.toString(), Find_Tuijian.class);

            return find_tuijien;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    public static List<Category> parseCategory(JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.optJSONArray("category");

        List<Category> categories = JSON.parseArray(jsonArray.toString(), Category.class);


        return categories;
    }

    public static List<Surging_Banner> parseSurging_Banner(JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.optJSONArray("banner");
        List<Surging_Banner> surging_banners = JSON.parseArray(jsonArray.toString(), Surging_Banner.class);
        return surging_banners;
    }

    public static List<Surging_Hottest> parseSurging_Hottest(JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.optJSONArray("hottest");
        List<Surging_Hottest> surging_hottests = JSON.parseArray(jsonArray.toString(), Surging_Hottest.class);

        return surging_hottests;
    }


    public static List<Surging_Context> parseContext(String res) {

        try {
            JSONObject jsonObject=new JSONObject(res);

            JSONArray jsonArray = jsonObject.optJSONArray("data");

            List<Surging_Context> surging_contexts=JSON.parseArray(jsonArray.toString(),Surging_Context.class);

            return surging_contexts;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static List<Surging_Special_column> parseSurging_Special_column(JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.optJSONArray("special_column");
        List<Surging_Special_column> surging_special_columns = JSON.parseArray(jsonArray.toString(), Surging_Special_column.class);
        return surging_special_columns;
    }

    public static JSONObject parseSurging_Children_category(String result) {

        try {
            JSONObject jsonObject=new JSONObject(result);
            jsonObject = jsonObject.optJSONObject("data");
            return jsonObject;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static List<SurgingModelGoods> parseSurgingModelGoods(String result) {

        try {
            JSONObject jsonObject=new JSONObject(result);

            JSONArray jsonArray = jsonObject.optJSONArray("data");

            List<SurgingModelGoods> modelGoodses = JSON.parseArray(jsonArray.toString(), SurgingModelGoods.class);
            return modelGoodses;


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
