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
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

/**
 * The Class CardModelImpl.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "sherwin-williams/components/card")
@Exporter(name = "jackson", extensions = "json", options = {})
public class CardModelImpl {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	/** The elements. */
	@Optional
	@Inject
	@Named("multiNode")
	//@ChildResource(name = "elements",injectionStrategy =
	//InjectionStrategy.OPTIONAL)
	private Resource multiNode;

	/** The title. */
	@ValueMapValue

	@Optional

	@Named("title")

	private String title;

	@ValueMapValue

	@Optional

	@Named("description")

	private String description;

	/** The enable placeholder. */
	@ValueMapValue

	@Optional

	@Named("enablePlaceholder")

	private String enablePlaceholder;

	/** The placeholder title. */
	@ValueMapValue

	@Optional

	@Named("placeholderTitle")

	private String placeholderTitle;

	/** The placeholder description. */
	@ValueMapValue

	@Optional

	@Named("placeholderDescription")

	private String placeholderDescription;

	/** The file reference. */
	@ValueMapValue

	@Optional

	@Named("fileReference")

	private String fileReference;

	/** The alt. */
	@ValueMapValue

	@Optional

	@Named("alt")

	private String alt;

	@SuppressWarnings("deprecation")
	@PostConstruct
	protected void init() {
		if(null != multiNode) {
			final Node node = multiNode.adaptTo(Node.class);
			final StringWriter stringWriter = new StringWriter();
			final JsonItemWriter jsonWriter = new JsonItemWriter(null);
			JSONObject jsonObject = null;
			try {
				/* Get JSON with no limit to recursion depth. */
				jsonWriter.dump(node, stringWriter, -1);
				jsonObject = new JSONObject(stringWriter.toString());
				Node parentNode = node.getParent();
				parentNode.setProperty("elements", jsonObject.toString());
				parentNode.getSession().save();
			} catch (Exception e) {
				logger.error("Could not create JSON", e);
			}
		}

	}

	/**
	 * 
	 * 
	 * 
	 * /** Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the enable placeholder.
	 *
	 * @return the enable placeholder
	 */
	public String getEnablePlaceholder() {
		return enablePlaceholder;
	}

	/**
	 * Sets the enable placeholder.
	 *
	 * @param enablePlaceholder the new enable placeholder
	 */
	public void setEnablePlaceholder(String enablePlaceholder) {
		this.enablePlaceholder = enablePlaceholder;
	}

	/**
	 * Gets the placeholder title.
	 *
	 * @return the placeholder title
	 */
	public String getPlaceholderTitle() {
		return placeholderTitle;
	}

	/**
	 * Sets the placeholder title.
	 *
	 * @param placeholderTitle the new placeholder title
	 */
	public void setPlaceholderTitle(String placeholderTitle) {
		this.placeholderTitle = placeholderTitle;
	}

	/**
	 * Gets the placeholder description.
	 *
	 * @return the placeholder description
	 */
	public String getPlaceholderDescription() {
		return placeholderDescription;
	}

	/**
	 * Sets the placeholder description.
	 *
	 * @param placeholderDescription the new placeholder description
	 */
	public void setPlaceholderDescription(String placeholderDescription) {
		this.placeholderDescription = placeholderDescription;
	}

	/**
	 * Gets the file reference.
	 *
	 * @return the file reference
	 */
	public String getFileReference() {
		return fileReference;
	}

	/**
	 * Sets the file reference.
	 *
	 * @param fileReference the new file reference
	 */
	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}

	/**
	 * Gets the alt.
	 *
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * Sets the alt.
	 *
	 * @param alt the new alt
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	public Resource getMultiNode() {
		return multiNode;
	}

	public void setMultiNode(Resource multiNode) {
		this.multiNode = multiNode;
	}

}