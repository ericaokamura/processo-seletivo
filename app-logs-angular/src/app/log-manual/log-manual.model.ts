export class LogManualModel {

    private date: string;
    
    private ip: string;
    
    private request: string;
    
    private status: number;
    
    private user_agent: string;
    
    constructor(date: string, ip: string, request: string, status: number, user_agent: string) {
        this.date = date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.user_agent = user_agent;
    }

    public getDate(): string {
        return this.date;
    }
    public setDate(date: string) {
        this.date = date;
    }
    public getIp(): string {
        return this.ip;
    }
    public setIp(ip: string) {
        this.ip = ip;
    }
    public getRequest(): string {
        return this.request;
    }
    public setRequest(request: string) {
        this.request = request;
    }
    public getStatus(): number {
        return this.status;
    }
    public setStatus(status: number) {
        this.status = status;
    }
    public getUserAgent(): string {
        return this.user_agent;
    }
    public setUserAgent(user_agent: string) {
        this.user_agent = user_agent;
    }
}