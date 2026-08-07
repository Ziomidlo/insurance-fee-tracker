export interface FeeStatement {
    id: number;
    office: string;
    createdDate: string;
    installment: number;
    product: string;
    risk: string;
    collection: string;
    rate: string;
    commissionAmount: string;
    policy: number;

}

export interface SubmittedForm {
    id: number;
    confirmedDate: Date;
    collection: string;
    cash: string;
    paymentMethod: string;
    policy: number;

}

export interface Policy {
    id: number;
    policySeries: string;
    policyNumber: string;
    fullPolicyNumber : string;
    insuranceCompany: string;
    hasSubmittedForm: boolean;
    hasFeeStatements: boolean;

    feeStatements: FeeStatement[];
    submittedForms: SubmittedForm[];
}