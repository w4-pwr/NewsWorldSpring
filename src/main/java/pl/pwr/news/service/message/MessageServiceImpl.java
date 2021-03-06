package pl.pwr.news.service.message;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Inject
	private MessageSource messageSource;

	@Override
	public String getMessage(String id) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, null, locale);
	}

}
