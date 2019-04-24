package sxs.xas.bqq.hqz.yjgc.myw.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * 解析Header： Authorization
 * 
 * @author wang
 *
 */
public class ProductPreOauth2ResourceFilter extends AbstractPreAuthenticatedProcessingFilter {

	private TokenExtractor tokenExtractor = new BearerTokenExtractor();

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		Authentication authentication = tokenExtractor.extract(request);
		if (authentication != null) {
			Object obj = authentication.getPrincipal();

			return obj;
		}
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

		return "N/A";
	}
}
