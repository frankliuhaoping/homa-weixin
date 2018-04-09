package cn.cnyirui.framework.extension.hibernate.uuid;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.fasterxml.uuid.Generators;

public class TimeBasedUUIDGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		return Generators.timeBasedGenerator().generate().toString();
	}

}
