package com.ming.tools;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**k8s 操作工具类
 * 
 *
 * @author ming
 * @date 2018-02-05 13:25
 */
public class K8sUtils {


    public KubernetesClient getKubernetesClient(){

        return new DefaultKubernetesClient();
    }
}
