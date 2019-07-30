package xyz.weechang.paddling.core.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:29.
 */
@Data
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 5966306766659220492L;

    /*** 创建时间 */
    protected Date createdDate;

    /*** 创建人 */
    protected Long createdBy;

    /*** 更新时间 */
    protected Date modifiedDate;

    /*** 更新人 */
    protected Long modifiedBy;

    /*** 逻辑删除 */
    @TableLogic
    protected Integer logic;

    /*** 扩展参数 */
    @TableField(exist = false)
    protected Map<String, Object> extData;

    public void addExtData(String key, Object value){
        if (extData == null) new HashMap<>();
        extData.put(key, value);
    }
}
