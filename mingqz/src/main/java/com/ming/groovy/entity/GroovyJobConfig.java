package com.ming.groovy.entity;

import com.google.common.base.Preconditions;
import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * groovy job 配置
 *
 * @author ming
 * @date 2017-11-13 17:21
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class GroovyJobConfig extends InId {
    /**
     * 脚本名称
     */
    private String name;
    /**
     * 注册到容器的名称
     */
    private String beanName;
    /**
     * 脚本内容
     */
    private String scriptContent;
    /**
     * 备注
     */
    private String memo;
    /**
     * 注册状态
     */
    private Integer registerStatus;
    /**
     * 脚本状态
     */
    private Integer status;

    /**
     * 检测 保存时候输入参数
     *
     * @author ming
     * @date 2017-12-14 16:34
     */
    public void checkCreate() {
        Preconditions.checkNotNull(this, "必须传入groovy job 配置");
        Preconditions.checkNotNull(getName(), "脚本必须存在名称");
        Preconditions.checkNotNull(getBeanName(), "脚本注入spring 的bean 名称必须存在");
        Preconditions.checkNotNull(getScriptContent(), "脚本内容必须存在");
    }

    /**
     * 检测 保存时候输入参数
     *
     * @author ming
     * @date 2017-12-14 16:34
     */
    public void checkUpdate() {
        Preconditions.checkNotNull(this, "必须传入groovy job 配置");
        Preconditions.checkNotNull(this.getId(), "修改groovy job 信息 id 必须存在");
        Preconditions.checkNotNull(getName(), "脚本必须存在名称");
        Preconditions.checkNotNull(getBeanName(), "脚本注入spring 的bean 名称必须存在");
        Preconditions.checkNotNull(getScriptContent(), "脚本内容必须存在");
    }


    @Getter
    public enum RegisterStatus {

        /**
         * 已经注册到spring bean中
         */
        REGISTER(1, "已注册到容器"),
        /**
         * 未注册到容器中
         */
        NO_REGISTER(2, "未注册到容器");
        private Integer key;
        private String value;

        RegisterStatus(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    @Getter
    public enum Status {
        /**
         * 有效状态
         */
        VALID(1, "有效"),
        /**
         * 无效
         */
        IN_VALID(2, "无效");

        private Integer key;
        private String value;

        Status(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }


}
