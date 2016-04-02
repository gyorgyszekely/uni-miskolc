package hu.miskolc.uni.iit.hydrominder.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class HydrominderAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private static final Logger logger =  LoggerFactory.getLogger(HydrominderAuthenticationFilter.class);
	
	private static final String PROTECTED_API = "/mobilegateway/**";
	
	public HydrominderAuthenticationFilter() {
		super(HydrominderAuthenticationFilter.PROTECTED_API);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		final JsonObject jsonRequest =  JSONRequestProcessor(request, MediaType.APPLICATION_JSON);
		logger.debug("Catch incoming JSON request: {}", jsonRequest.toString());
		AuthenticationManager authenticationManager = getAuthenticationManager();
		Assert.notNull(authenticationManager, "Customized authentication manager cannot be null.");
		//REVIEW: token filter will goes here?
		final String principal = jsonRequest.get("principal").getAsJsonPrimitive().getAsString();
		final String credential = jsonRequest.get("credential").getAsJsonPrimitive().getAsString();
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal, credential));
		return authenticate;
	}

	private JsonObject JSONRequestProcessor(HttpServletRequest request, MediaType mediaType)
	{
		final JsonObject preparedJsonRequestObject;
		try
		{
			final InputStream requestInputStream = request.getInputStream();
			JsonParser gsonParser = new JsonParser();
			final JsonElement marshalledJson = gsonParser.parse(new InputStreamReader(requestInputStream, Charset.forName("ISO-8859-1")));
			preparedJsonRequestObject = marshalledJson.getAsJsonObject();
			final String trimmedJsonStr = preparedJsonRequestObject.toString().trim();
			if(trimmedJsonStr.isEmpty() || !((trimmedJsonStr.charAt(0) == '{') && (trimmedJsonStr.charAt(trimmedJsonStr.length()-1) == '}')))
			{
				throw new IllegalArgumentException("Malformed or empty JSON. \n" + trimmedJsonStr);
			}

		}catch(Exception ex)
		{
			throw new IllegalStateException("An error occured when application tries to process incoming JSON request.", ex);
		}
		return preparedJsonRequestObject;
	}
	
	

}
