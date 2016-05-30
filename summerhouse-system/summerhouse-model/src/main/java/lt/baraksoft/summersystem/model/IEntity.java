package lt.baraksoft.summersystem.model;

/**
 * Created by Zygimantas on 2016.03.18.
 */
import java.io.Serializable;

public interface IEntity<K extends Serializable> extends Serializable {

        /**
         * Property which represents id.
         */
        String P_ID = "id";

        /**
         * Get primary key.
         *
         * @return primary key
         */
        K getId();

        /**
         * Set primary key.
         *
         * @param id primary key
         */
        void setId(K id);

        /**
         * Get persisted entity version.
         *
         * @return entity version, <code>null</code> if entity is not persisted yet
         */
        Integer getVersion();
}
