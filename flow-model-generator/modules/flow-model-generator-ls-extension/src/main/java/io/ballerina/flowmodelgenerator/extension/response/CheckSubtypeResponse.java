package io.ballerina.flowmodelgenerator.extension.response;

public class CheckSubtypeResponse extends AbstractFlowModelResponse {
    private boolean isSubtype;

    public CheckSubtypeResponse() {
    }

    public boolean isSubtype() {
        return this.isSubtype;
    }

    public void setIsSubtype(boolean isSubtype) {
        this.isSubtype = isSubtype;
    }
}
