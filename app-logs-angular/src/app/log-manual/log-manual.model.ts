export class LogManualModel {

    private date: string;
    
    private ip: string;
    
    private request: string;
    
    private status: number;
    
    private userAgent: string;
    
    constructor(date: string, ip: string, request: string, status: number, userAgent: string) {
        this.date = date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
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
        return this.userAgent;
    }
    public setUserAgent(userAgent: string) {
        this.userAgent = userAgent;
    }
}