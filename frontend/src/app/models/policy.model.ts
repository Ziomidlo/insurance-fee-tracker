import { FeeStatement } from "./feeStatement.model";
import { SubmittedForm } from "./subittedForm.model";

export interface Policy {
    id: number;
    policySeries: string;
    policyNumber: string;
    fullPolicyNumber : string;
    insuranceCompany: string;
    hasSubmittedForm: boolean;
    hasFeeStatements: boolean;
}