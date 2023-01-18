package com.adobe.aem.guides.sherwinwilliams.core.models;

import java.io.StringWriter;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Node;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.jcr.JsonItemWriter;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

/**
 * The Class MenuModelImpl.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "sherwin-williams/components/menu")
@Exporter(name = "jackson", extensions = "json", options = {})
public class MenuModelImpl {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** The links. */
	@Optional
	@Inject
	@Named("links")
	//@ChildResource(name = "elements",injectionStrategy =
	//InjectionStrategy.OPTIONAL)
	private Resource links;


	/**
	 * Inits the.
	 */
	@SuppressWarnings("deprecation")
	@PostConstruct
	protected void init() {
		final String METHOD_NAME = "activate(MenuModelImpl)";
		if(null != links) {
			final Node node = links.adaptTo(Node.class);
			final StringWriter stringWriter = new StringWriter();
			final JsonItemWriter jsonWriter = new JsonItemWriter(null);
			JSONObject jsonObject = null;
			try {
				/* Get JSON with no limit to recursion depth. */
				jsonWriter.dump(node, stringWriter, -1);
				jsonObject = new JSONObject(stringWriter.toString());
				Node parentNode = node.getParent();
				/*
				 * Gson gson = new
				 * GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization().create()
				 * ; //disableHtmlEscaping in JSON parentNode.setProperty("elements",
				 * gson.toJson(stringWriter.toString()));
				 */
				parentNode.setProperty("menuLinkItems", jsonObject.toString());
				parentNode.getSession().save();
			} catch (Exception e) {
				logger.error("Could not create JSON", e);
			}
		}

	}



	/**
	 * Gets the links.
	 *
	 * @return the links
	 */
	public Resource getLinks() {
		return links;
	}

	/**
	 * Sets the links.
	 *
	 * @param links the new links
	 */
	public void setLinks(Resource links) {
		this.links = links;
	}

}