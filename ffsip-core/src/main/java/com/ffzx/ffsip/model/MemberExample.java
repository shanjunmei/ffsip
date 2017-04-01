package com.ffzx.ffsip.model;

import com.ffzx.ffsip.model.MemberExample.Criteria;
import com.ffzx.orm.common.GenericExample.GeneratedCriteria;
import com.ffzx.orm.common.GenericExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberExample extends GenericExample<Criteria> {

    public MemberExample() {
        oredCriteria = new ArrayList<MemberExample.Criteria>();
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andFansNumIsNull() {
            addCriterion("fans_num is null");
            return (Criteria) this;
        }

        public Criteria andFansNumIsNotNull() {
            addCriterion("fans_num is not null");
            return (Criteria) this;
        }

        public Criteria andFansNumEqualTo(Integer value) {
            addCriterion("fans_num =", value, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumNotEqualTo(Integer value) {
            addCriterion("fans_num <>", value, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumGreaterThan(Integer value) {
            addCriterion("fans_num >", value, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("fans_num >=", value, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumLessThan(Integer value) {
            addCriterion("fans_num <", value, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumLessThanOrEqualTo(Integer value) {
            addCriterion("fans_num <=", value, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumIn(List<Integer> values) {
            addCriterion("fans_num in", values, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumNotIn(List<Integer> values) {
            addCriterion("fans_num not in", values, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumBetween(Integer value1, Integer value2) {
            addCriterion("fans_num between", value1, value2, "fansNum");
            return (Criteria) this;
        }

        public Criteria andFansNumNotBetween(Integer value1, Integer value2) {
            addCriterion("fans_num not between", value1, value2, "fansNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumIsNull() {
            addCriterion("subscribe_num is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumIsNotNull() {
            addCriterion("subscribe_num is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumEqualTo(Integer value) {
            addCriterion("subscribe_num =", value, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumNotEqualTo(Integer value) {
            addCriterion("subscribe_num <>", value, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumGreaterThan(Integer value) {
            addCriterion("subscribe_num >", value, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("subscribe_num >=", value, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumLessThan(Integer value) {
            addCriterion("subscribe_num <", value, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumLessThanOrEqualTo(Integer value) {
            addCriterion("subscribe_num <=", value, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumIn(List<Integer> values) {
            addCriterion("subscribe_num in", values, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumNotIn(List<Integer> values) {
            addCriterion("subscribe_num not in", values, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumBetween(Integer value1, Integer value2) {
            addCriterion("subscribe_num between", value1, value2, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andSubscribeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("subscribe_num not between", value1, value2, "subscribeNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumIsNull() {
            addCriterion("article_num is null");
            return (Criteria) this;
        }

        public Criteria andArticleNumIsNotNull() {
            addCriterion("article_num is not null");
            return (Criteria) this;
        }

        public Criteria andArticleNumEqualTo(Integer value) {
            addCriterion("article_num =", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumNotEqualTo(Integer value) {
            addCriterion("article_num <>", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumGreaterThan(Integer value) {
            addCriterion("article_num >", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_num >=", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumLessThan(Integer value) {
            addCriterion("article_num <", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumLessThanOrEqualTo(Integer value) {
            addCriterion("article_num <=", value, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumIn(List<Integer> values) {
            addCriterion("article_num in", values, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumNotIn(List<Integer> values) {
            addCriterion("article_num not in", values, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumBetween(Integer value1, Integer value2) {
            addCriterion("article_num between", value1, value2, "articleNum");
            return (Criteria) this;
        }

        public Criteria andArticleNumNotBetween(Integer value1, Integer value2) {
            addCriterion("article_num not between", value1, value2, "articleNum");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIsNull() {
            addCriterion("wx_headimgurl is null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIsNotNull() {
            addCriterion("wx_headimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlEqualTo(String value) {
            addCriterion("wx_headimgurl =", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotEqualTo(String value) {
            addCriterion("wx_headimgurl <>", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlGreaterThan(String value) {
            addCriterion("wx_headimgurl >", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("wx_headimgurl >=", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLessThan(String value) {
            addCriterion("wx_headimgurl <", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLessThanOrEqualTo(String value) {
            addCriterion("wx_headimgurl <=", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLike(String value) {
            addCriterion("wx_headimgurl like", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotLike(String value) {
            addCriterion("wx_headimgurl not like", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIn(List<String> values) {
            addCriterion("wx_headimgurl in", values, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotIn(List<String> values) {
            addCriterion("wx_headimgurl not in", values, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlBetween(String value1, String value2) {
            addCriterion("wx_headimgurl between", value1, value2, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotBetween(String value1, String value2) {
            addCriterion("wx_headimgurl not between", value1, value2, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIsNull() {
            addCriterion("wx_openid is null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIsNotNull() {
            addCriterion("wx_openid is not null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidEqualTo(String value) {
            addCriterion("wx_openid =", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotEqualTo(String value) {
            addCriterion("wx_openid <>", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThan(String value) {
            addCriterion("wx_openid >", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_openid >=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThan(String value) {
            addCriterion("wx_openid <", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThanOrEqualTo(String value) {
            addCriterion("wx_openid <=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLike(String value) {
            addCriterion("wx_openid like", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotLike(String value) {
            addCriterion("wx_openid not like", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIn(List<String> values) {
            addCriterion("wx_openid in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotIn(List<String> values) {
            addCriterion("wx_openid not in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidBetween(String value1, String value2) {
            addCriterion("wx_openid between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotBetween(String value1, String value2) {
            addCriterion("wx_openid not between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxNickNameIsNull() {
            addCriterion("wx_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andWxNickNameIsNotNull() {
            addCriterion("wx_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andWxNickNameEqualTo(String value) {
            addCriterion("wx_nick_name =", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotEqualTo(String value) {
            addCriterion("wx_nick_name <>", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameGreaterThan(String value) {
            addCriterion("wx_nick_name >", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("wx_nick_name >=", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameLessThan(String value) {
            addCriterion("wx_nick_name <", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameLessThanOrEqualTo(String value) {
            addCriterion("wx_nick_name <=", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameLike(String value) {
            addCriterion("wx_nick_name like", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotLike(String value) {
            addCriterion("wx_nick_name not like", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameIn(List<String> values) {
            addCriterion("wx_nick_name in", values, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotIn(List<String> values) {
            addCriterion("wx_nick_name not in", values, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameBetween(String value1, String value2) {
            addCriterion("wx_nick_name between", value1, value2, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotBetween(String value1, String value2) {
            addCriterion("wx_nick_name not between", value1, value2, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIsNull() {
            addCriterion("wx_subscribe_time is null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIsNotNull() {
            addCriterion("wx_subscribe_time is not null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeEqualTo(Date value) {
            addCriterion("wx_subscribe_time =", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotEqualTo(Date value) {
            addCriterion("wx_subscribe_time <>", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeGreaterThan(Date value) {
            addCriterion("wx_subscribe_time >", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("wx_subscribe_time >=", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeLessThan(Date value) {
            addCriterion("wx_subscribe_time <", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeLessThanOrEqualTo(Date value) {
            addCriterion("wx_subscribe_time <=", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIn(List<Date> values) {
            addCriterion("wx_subscribe_time in", values, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotIn(List<Date> values) {
            addCriterion("wx_subscribe_time not in", values, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeBetween(Date value1, Date value2) {
            addCriterion("wx_subscribe_time between", value1, value2, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotBetween(Date value1, Date value2) {
            addCriterion("wx_subscribe_time not between", value1, value2, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxCityIsNull() {
            addCriterion("wx_city is null");
            return (Criteria) this;
        }

        public Criteria andWxCityIsNotNull() {
            addCriterion("wx_city is not null");
            return (Criteria) this;
        }

        public Criteria andWxCityEqualTo(String value) {
            addCriterion("wx_city =", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotEqualTo(String value) {
            addCriterion("wx_city <>", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityGreaterThan(String value) {
            addCriterion("wx_city >", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityGreaterThanOrEqualTo(String value) {
            addCriterion("wx_city >=", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLessThan(String value) {
            addCriterion("wx_city <", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLessThanOrEqualTo(String value) {
            addCriterion("wx_city <=", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLike(String value) {
            addCriterion("wx_city like", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotLike(String value) {
            addCriterion("wx_city not like", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityIn(List<String> values) {
            addCriterion("wx_city in", values, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotIn(List<String> values) {
            addCriterion("wx_city not in", values, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityBetween(String value1, String value2) {
            addCriterion("wx_city between", value1, value2, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotBetween(String value1, String value2) {
            addCriterion("wx_city not between", value1, value2, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCountryIsNull() {
            addCriterion("wx_country is null");
            return (Criteria) this;
        }

        public Criteria andWxCountryIsNotNull() {
            addCriterion("wx_country is not null");
            return (Criteria) this;
        }

        public Criteria andWxCountryEqualTo(String value) {
            addCriterion("wx_country =", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotEqualTo(String value) {
            addCriterion("wx_country <>", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryGreaterThan(String value) {
            addCriterion("wx_country >", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryGreaterThanOrEqualTo(String value) {
            addCriterion("wx_country >=", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLessThan(String value) {
            addCriterion("wx_country <", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLessThanOrEqualTo(String value) {
            addCriterion("wx_country <=", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLike(String value) {
            addCriterion("wx_country like", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotLike(String value) {
            addCriterion("wx_country not like", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryIn(List<String> values) {
            addCriterion("wx_country in", values, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotIn(List<String> values) {
            addCriterion("wx_country not in", values, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryBetween(String value1, String value2) {
            addCriterion("wx_country between", value1, value2, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotBetween(String value1, String value2) {
            addCriterion("wx_country not between", value1, value2, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIsNull() {
            addCriterion("wx_province is null");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIsNotNull() {
            addCriterion("wx_province is not null");
            return (Criteria) this;
        }

        public Criteria andWxProvinceEqualTo(String value) {
            addCriterion("wx_province =", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotEqualTo(String value) {
            addCriterion("wx_province <>", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceGreaterThan(String value) {
            addCriterion("wx_province >", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("wx_province >=", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLessThan(String value) {
            addCriterion("wx_province <", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLessThanOrEqualTo(String value) {
            addCriterion("wx_province <=", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLike(String value) {
            addCriterion("wx_province like", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotLike(String value) {
            addCriterion("wx_province not like", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIn(List<String> values) {
            addCriterion("wx_province in", values, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotIn(List<String> values) {
            addCriterion("wx_province not in", values, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceBetween(String value1, String value2) {
            addCriterion("wx_province between", value1, value2, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotBetween(String value1, String value2) {
            addCriterion("wx_province not between", value1, value2, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByIsNull() {
            addCriterion("last_update_by is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByIsNotNull() {
            addCriterion("last_update_by is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByEqualTo(String value) {
            addCriterion("last_update_by =", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotEqualTo(String value) {
            addCriterion("last_update_by <>", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByGreaterThan(String value) {
            addCriterion("last_update_by >", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("last_update_by >=", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByLessThan(String value) {
            addCriterion("last_update_by <", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByLessThanOrEqualTo(String value) {
            addCriterion("last_update_by <=", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByLike(String value) {
            addCriterion("last_update_by like", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotLike(String value) {
            addCriterion("last_update_by not like", value, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByIn(List<String> values) {
            addCriterion("last_update_by in", values, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotIn(List<String> values) {
            addCriterion("last_update_by not in", values, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByBetween(String value1, String value2) {
            addCriterion("last_update_by between", value1, value2, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateByNotBetween(String value1, String value2) {
            addCriterion("last_update_by not between", value1, value2, "lastUpdateBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateIsNull() {
            addCriterion("last_update_date is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateIsNotNull() {
            addCriterion("last_update_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateEqualTo(Date value) {
            addCriterion("last_update_date =", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateNotEqualTo(Date value) {
            addCriterion("last_update_date <>", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateGreaterThan(Date value) {
            addCriterion("last_update_date >", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_date >=", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateLessThan(Date value) {
            addCriterion("last_update_date <", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("last_update_date <=", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateIn(List<Date> values) {
            addCriterion("last_update_date in", values, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateNotIn(List<Date> values) {
            addCriterion("last_update_date not in", values, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateBetween(Date value1, Date value2) {
            addCriterion("last_update_date between", value1, value2, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("last_update_date not between", value1, value2, "lastUpdateDate");
            return (Criteria) this;
        }
    }
}