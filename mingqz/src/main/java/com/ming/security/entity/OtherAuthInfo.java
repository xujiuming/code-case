package com.ming.security.entity;


import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * other auth
 * open id
 *
 * @author ming
 * @date 2018-01-06 16:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class OtherAuthInfo extends InId {
    /**
     * user id
     * {@linkplain User#id}
     */
    private Long userId;
    /**
     * open id
     */
    private String openId;
    /**
     * open id head image url
     */
    private String openHeadImageUrl;
    /**
     * plat form
     * {@link PlatForm}
     */
    private String platForm;
    /**
     * memo
     */
    private String memo;


    @Getter
    public enum PlatForm {
        QQ(1, "qq"),
        WEIXIN(2, "weixin"),
        GITHUB(3, "github"),
        WANGYIEMAIL(4, "wangyi  email"),;
        private Integer type;
        private String name;

        PlatForm(Integer type, String name) {
            this.type = type;
            this.name = name;
        }
    }


}
