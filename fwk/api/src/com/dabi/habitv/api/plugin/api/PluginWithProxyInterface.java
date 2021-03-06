package com.dabi.habitv.api.plugin.api;

import java.util.Map;

import com.dabi.habitv.api.plugin.dto.ProxyDTO;
import com.dabi.habitv.api.plugin.dto.ProxyDTO.ProtocolEnum;

public interface PluginWithProxyInterface {

	/**
	 * set the proxy list to use by protocol
	 * 
	 * @param protocol2proxy
	 *            proxy list
	 */
	void setProxies(Map<ProtocolEnum, ProxyDTO> protocol2proxy);
}
